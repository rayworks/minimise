import co.joebirch.minimise.buildsrc.Deps

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()
    if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true) {
        iosArm64()
    } else {
        iosSimulatorArm64()
    }

    sourceSets {
        jvmMain.dependencies {
            implementation(Deps.Coroutines.android)
            implementation(Deps.Coroutines.core)
        }
    }
}
