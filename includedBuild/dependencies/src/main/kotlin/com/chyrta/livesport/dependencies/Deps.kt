object App {
    const val minSdkVersion = 23
    const val targetSdkVersion = 33
    const val compileSdkVersion = targetSdkVersion
}

object Deps {
    private const val kotlinVersion = "1.7.20"
    private const val detektVersion = "1.21.0"

    val kotlin = Kotlin
    val kotlinX = KotlinX
    val android = Android
    val kmmBridge = KmmBridge
    val core = Core
    val kiwi = Kiwi
    val network = Network
    val image = Image
    val di = Di
    val quality = Quality

    object Kotlin {
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object KotlinX {
        private const val coroutinesVersion = "1.6.4"
        private const val coroutinesNativeVersion = "1.6.3-native-mt"
        private const val kotlinSerializationVersion = "1.4.1"
        private const val dateTimeVersion = "0.4.0"

        val coroutines = Coroutines
        val serialization = Serialization
        val dateTime = DateTime

        object Coroutines {
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
            const val core =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesNativeVersion"
        }

        object Serialization {
            const val plugin = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
            const val serialization =
                "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion"
        }

        object DateTime {
            const val dateTime =
                "org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion"
        }
    }

    object Network {
        private const val ktorVersion = "2.1.3"

        val ktor = Ktor

        object Ktor {
            const val core = "io.ktor:ktor-client-core:$ktorVersion"
            const val okHttp = "io.ktor:ktor-client-okhttp:$ktorVersion"
            const val darwin = "io.ktor:ktor-client-darwin:$ktorVersion"
            const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
            const val json = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
            const val mock = "io.ktor:ktor-client-mock:$ktorVersion"
        }
    }

    object Android {
        private const val gradlePluginVersion = "7.3.1"
        private const val composeVersion = "1.3.1"
        const val composeCompilerVersion = "1.3.2"
        private const val composeConstraintLayoutVersion = "1.0.1"
        private const val androidxCoreVersion = "1.9.0"
        private const val materialDesignVersion = "1.7.0"
        private const val accompanistVersion = "0.27.0"

        const val plugin = "com.android.tools.build:gradle:$gradlePluginVersion"

        val androidX = AndroidX
        val compose = Compose
        val accompanist = Accompanist

        object AndroidX {
            const val core = "androidx.core:core-ktx:$androidxCoreVersion"
            const val material = "com.google.android.material:material:$materialDesignVersion"
        }

        object Accompanist {
            const val accompanistSystemController =
                "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
        }

        object Compose {
            const val ui = "androidx.compose.ui:ui:$composeVersion"
            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout-compose:$composeConstraintLayoutVersion"
            const val navigationCompose = "androidx.navigation:navigation-compose:2.5.3"
            const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
            const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
            const val material3 = "androidx.compose.material3:material3:$composeVersion"
        }
    }

    object KmmBridge {
        private const val kmmBridgeVersion = "0.3.1"

        val plugin =
            "co.touchlab.faktory.kmmbridge:co.touchlab.faktory.kmmbridge.gradle.plugin:$kmmBridgeVersion"
    }

    object Image {
        private const val coilVersion = "2.2.2"

        const val coil = "io.coil-kt:coil-compose:2.2.2"
    }

    object Di {
        private const val koinCoreVersion = "3.2.2"
        private const val koinAndroidVersion = "3.3.0"

        val koin = Koin

        object Koin {
            const val koinCore = "io.insert-koin:koin-core:$koinCoreVersion"
            const val koinAndroid = "io.insert-koin:koin-android:$koinAndroidVersion"
            const val koinCompose = "io.insert-koin:koin-androidx-compose:$koinAndroidVersion"
            const val koinTest = "io.insert-koin:koin-test:$koinCoreVersion"
            const val koinJunit = "io.insert-koin:koin-test-junit5:$koinCoreVersion"
        }
    }

    object Core {
        private const val orbitMviVersion = "4.4.0"
        private const val klockVersion = "3.4.0"
        private const val timberVersion = "5.0.1"

        val orbit = Orbit
        val dateTime = DateTime
        val utils = Utils

        object Utils {
            const val timber = "com.jakewharton.timber:timber:$timberVersion"
        }

        object Orbit {
            const val core = "org.orbit-mvi:orbit-core:$orbitMviVersion"
            const val viewModel = "org.orbit-mvi:orbit-viewmodel:$orbitMviVersion"
            const val compose = "org.orbit-mvi:orbit-compose:$orbitMviVersion"
            const val test = "org.orbit-mvi:orbit-test:$orbitMviVersion"
        }

        object DateTime {
            const val klock = "com.soywiz.korlibs.klock:klock:$klockVersion"
        }
    }

    object Kiwi {
        private const val orbitVersion = "0.21.0"

        val orbit = Orbit

        object Orbit {
            const val ui = "kiwi.orbit.compose:ui:$orbitVersion"
            const val icons = "kiwi.orbit.compose:icons:$orbitVersion"
            const val illustrations = "kiwi.orbit.compose:illustrations:$orbitVersion"
        }
    }


    object Quality {
        private const val junit5RunnerVersion = "1.9.1"
        private const val junitJupiterVersion = "5.9.1"
        private const val coroutinesTestVersion = "1.6.4"
        private const val mockkVersion = "1.13.2"
        private const val suitesVersion = "1.8.1"
        private const val koverVersion = "0.6.1"
        private const val ktlintVersion = "11.0.0"
        private const val turbineVersion = "0.12.1"

        val detekt = Detekt
        val ktlint = KtLint
        val kover = Kover
        val unitTest = UnitTest

        object KtLint {
            const val plugin = "org.jlleitschuh.gradle:ktlint-gradle:$ktlintVersion"
        }

        object Kover {
            const val plugin = "org.jetbrains.kotlinx:kover:$koverVersion"
        }

        object Detekt {
            const val plugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion"
            const val ktlint = "io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion"
        }

        object UnitTest {
            const val turbine = "app.cash.turbine:turbine:$turbineVersion"
            const val coroutinesTest =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"
            const val mockk = "io.mockk:mockk:$mockkVersion"
            const val launcher = "org.junit.platform:junit-platform-launcher:$suitesVersion"
            const val junit5Runner = "org.junit.platform:junit-platform-runner:$junit5RunnerVersion"
            const val junitJupiterAPI = "org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion"
            const val junitJupiterEngine =
                "org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion"
            const val junitSuite = "org.junit.platform:junit-platform-suite:$suitesVersion"
        }
    }
}
