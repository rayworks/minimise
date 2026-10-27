package co.joebirch.minimise.buildsrc

object Versions {
    val minSdk = 24
    val targetSdk = 36
    val compileSdk = 37
    val kotlin = Deps.Kotlin.version
}

object Deps {

    const val androidGradlePlugin = "com.android.tools.build:gradle:9.4.1"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    const val composeCompilerGradlePlugin =
        "org.jetbrains.kotlin:compose-compiler-gradle-plugin:${Kotlin.version}"
    const val kspGradlePlugin = "com.google.devtools.ksp:symbol-processing-gradle-plugin:2.3.12"

    const val junit = "junit:junit:4.13.2"

    object Apollo {
        const val version = "5.2.0"
        const val plugin = "com.apollographql.apollo:apollo-gradle-plugin:$version"
        const val runtime = "com.apollographql.apollo:apollo-runtime:$version"
        const val api = "com.apollographql.apollo:apollo-api:$version"
    }

    object BuildKonfig {
        const val plugin = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.23.0"
    }

    object Kotlin {
        const val version = "2.4.21"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:$version"
    }

    object Lifecycle {
        const val lifecycleVersion = "2.11.0"

        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val common = "androidx.lifecycle:lifecycle-common:$lifecycleVersion"
        const val runtime = "androidx.lifecycle:lifecycle-runtime:$lifecycleVersion"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    }

    object AndroidX {
        const val annotation = "androidx.annotation:annotation:1.11.0"
        const val coreKtx = "androidx.core:core-ktx:1.19.1"
        const val appCompat = "androidx.appcompat:appcompat:1.8.0"

        const val activityCompose = "androidx.activity:activity-compose:1.13.0"

        const val datastorePreferences = "androidx.datastore:datastore-preferences:1.3.0"

        const val archCoreTesting = "androidx.arch.core:core-testing:2.2.0"

        const val junit = "androidx.test.ext:junit:1.3.0"
        const val testRunner = "androidx.test:runner:1.7.0"
        const val testRules = "androidx.test:rules:1.7.0"
    }

    object Navigation {
        const val compose = "androidx.navigation:navigation-compose:2.10.2"
    }

    object Espresso {
        const val version = "3.7.0"
        const val core = "androidx.test.espresso:espresso-core:$version"
    }

    object Mockito {
        const val version = "5.24.0"
        const val android = "org.mockito:mockito-android:$version"
    }

    object Fragment {
        private const val version = "1.9.1"

        const val base = "androidx.fragment:fragment:$version"
        const val ktx = "androidx.fragment:fragment-ktx:$version"
        const val testing = "androidx.fragment:fragment-testing:$version"
    }

    object Coroutines {
        const val version = "1.11.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object Compose {
        const val bom = "androidx.compose:compose-bom:2026.09.00"

        const val animation = "androidx.compose.animation:animation"
        const val animationCore = "androidx.compose.animation:animation-core"
        const val core = "androidx.compose.ui:ui"
        const val foundation = "androidx.compose.foundation:foundation"
        const val layout = "androidx.compose.foundation:foundation-layout"
        const val livedata = "androidx.compose.runtime:runtime-livedata"
        const val material = "androidx.compose.material:material"
        const val icons = "androidx.compose.material:material-icons-core"
        const val iconsExtended = "androidx.compose.material:material-icons-extended"
        const val runtime = "androidx.compose.runtime:runtime"
        const val tooling = "androidx.compose.ui:ui-tooling"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val test = "androidx.compose.ui:ui-test-junit4"
        const val testManifest = "androidx.compose.ui:ui-test-manifest"
    }

    object Google {
        val material = "com.google.android.material:material:1.14.0"

        object Hilt {
            private const val hiltVersion = "2.60.1"
            val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
            val hilt = "com.google.dagger:hilt-android:$hiltVersion"
            val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
            val viewModelCompose = "androidx.hilt:hilt-lifecycle-viewmodel-compose:1.4.0"
        }
    }
}
