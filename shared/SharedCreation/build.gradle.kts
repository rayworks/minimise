import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import co.joebirch.minimise.buildsrc.Deps

plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    val iosTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            iosArm64()
        else
            iosSimulatorArm64()

    iosTarget.binaries {
        framework("Authentication") {
            baseName = "Authentication"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(Deps.Coroutines.core)
            implementation(project(":shared:SharedCommon"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(Deps.Coroutines.core)
            implementation(Deps.Coroutines.test)
        }

        jvmMain.dependencies {
            implementation(Deps.Coroutines.android)
        }
    }
}

val packForXcode = tasks.register<Sync>("packForXcode") {
    val targetDir = layout.buildDirectory.dir("xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.withType<KotlinNativeTarget>()
        .single().binaries.getFramework("Authentication", NativeBuildType.valueOf(mode.uppercase()))
    inputs.property("mode", mode)
    dependsOn(framework.linkTaskProvider)

    from(framework.outputDirectory)
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    val javaHome = System.getProperty("java.home")
    val rootDir = rootProject.rootDir
    doLast {
        val gradlew = targetDir.get().file("gradlew").asFile
        gradlew.writeText("#!/bin/bash\nexport 'JAVA_HOME=$javaHome'\ncd '$rootDir'\n./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.named("build") { dependsOn(packForXcode) }
