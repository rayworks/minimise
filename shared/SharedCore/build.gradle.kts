import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import co.joebirch.minimise.buildsrc.Deps

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()

    //select iOS target platform depending on the Xcode environment variables
    val iosTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            iosArm64()
        else
            iosSimulatorArm64()

    iosTarget.binaries {
        framework {
            baseName = "SharedCode"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(Deps.Coroutines.core)
        }

        iosMain.dependencies {
            api(project(":shared:SharedAuthentication"))
        }
    }
}

val packForXcode = tasks.register<Sync>("packForXcode") {
    group = "build"

    //selecting the right configuration for the iOS framework depending on the Xcode environment variables
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>()
        .single().binaries.getFramework(NativeBuildType.valueOf(mode.uppercase()))

    inputs.property("mode", mode)
    dependsOn(framework.linkTaskProvider)

    val targetDir = layout.buildDirectory.dir("xcode-frameworks")
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
