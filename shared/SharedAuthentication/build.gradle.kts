import co.joebirch.minimise.buildsrc.Deps
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.util.Properties

plugins {
    id("org.jetbrains.kotlin.native.cocoapods")
    id("com.apollographql.apollo")
    kotlin("multiplatform")
    id("com.codingfeline.buildkonfig")
}

version = "1.0"
group = "co.joebirch.minimise.authentication"

kotlin {
    jvm()
    if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true) {
        iosArm64()
    } else {
        iosSimulatorArm64()
    }

    cocoapods {
        summary = "Shared Authentication"
        homepage = "www.joebirch.co"
        framework {
            baseName = "SharedAuthentication"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(Deps.Coroutines.core)
            implementation(Deps.Apollo.api)
            implementation(Deps.Apollo.runtime)
            implementation(project(":shared:SharedCommon"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(Deps.Coroutines.core)
        }
    }
}

apollo {
    service("minimise") {
        packageName.set("co.joebirch.minimise")
    }
}

buildkonfig {
    packageName = "co.joebirch.minimise"
    val props = Properties().apply {
        rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
    }
    defaultConfigs {
        buildConfigField(STRING, "apiUrl", props.getProperty("API_URL") ?: "")
    }
}
