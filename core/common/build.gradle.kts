plugins {
    id("multiplatform-configuration")
}

android {
    namespace = "com.chyrta.livesport.common"
}

kotlin {
    android()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Deps.kotlinX.coroutines.core)
                api(Deps.kotlinX.dateTime.dateTime)
                api(Deps.core.dateTime.klock)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(Deps.kotlinX.coroutines.android)
                api(Deps.di.koin.koinAndroid)
                api(Deps.di.koin.koinCompose)
            }
        }
        val androidTest by getting
        val iosMain by getting
        val iosTest by getting
        val iosSimulatorArm64Main by getting
        val iosSimulatorArm64Test by getting
    }
}
