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
                api(Deps.di.koin.koinCore)
                api(Deps.kotlinX.coroutines.core)
                api(Deps.kotlinX.dateTime.dateTime)
                api(Deps.kotlinX.serialization.serialization)
                api(Deps.core.dateTime.klock)
                api(Deps.network.ktor.core)
                api(Deps.network.ktor.json)
                api(Deps.network.ktor.contentNegotiation)
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
                api(Deps.network.ktor.okHttp)
            }
        }
        val androidTest by getting
        val iosMain by getting
        val iosTest by getting
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
        val iosSimulatorArm64Test by getting
        iosSimulatorArm64Test.dependsOn(iosTest)
    }
}
