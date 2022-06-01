package com.nonofy

object Libs {
    object Compose {
        private const val composeVersion = "1.1.1"

        const val compiler = "androidx.compose.compiler:compiler:$composeVersion"
        const val activity = "androidx.activity:activity-compose:1.4.0"
        const val ui = "androidx.compose.ui:ui:$composeVersion"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"

        const val navigation = "androidx.navigation:navigation-compose:2.4.2"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"

        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
        const val liveData = "androidx.compose.runtime:runtime-livedata:$composeVersion"
        const val runtime = "androidx.compose.runtime:runtime:$composeVersion"

        const val uiTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    }

    object Material {
        const val core = "com.google.android.material:material:1.4.0"
    }

    object AppCompat {
        const val core = "androidx.appcompat:appcompat:1.3.0"
    }

    object Ktx {
        const val core = "androidx.core:core-ktx:1.6.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.6"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
    }

    object Hilt {
        const val version = "2.42"

        const val core = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val jetpack = "androidx.hilt:hilt-compiler:1.0.0"
    }

    object Datastore {
        private const val version = "1.0.0"

        const val core = "androidx.datastore:datastore:$version"
        const val preferences = "androidx.datastore:datastore-preferences:$version"
    }

    object Protobuf {
        private const val version = "3.20.0"

        const val core = "com.google.protobuf:protobuf-javalite:$version"
    }

    object JUnit {
        const val core = "junit:junit:4.13.2"
        const val ui = "androidx.test.ext:junit:1.1.3"
    }

    object Espresso {
        const val core = "androidx.test.espresso:espresso-core:3.4.0"
    }
}