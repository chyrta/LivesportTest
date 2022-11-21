plugins {
    id("multiplatform-configuration")
}

android {
    namespace = "com.chyrta.livesport.search.logic"
}

kotlin {
    android()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:common"))
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation(project(":core:common"))
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Deps.di.koin.koinTest)
                implementation(Deps.di.koin.koinJunit)
                implementation(Deps.quality.unitTest.turbine)
                implementation(Deps.quality.unitTest.mockk)
                implementation(Deps.quality.unitTest.junit5Runner)
                implementation(Deps.quality.unitTest.coroutinesTest)
                implementation(Deps.quality.unitTest.junitJupiterAPI)
                implementation(Deps.quality.unitTest.junitJupiterEngine)
            }
        }

        val iosMain by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosTest by getting
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }
    }
}
