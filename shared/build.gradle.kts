plugins {
    id("multiplatform-configuration")
}

kotlin {
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosMain by getting
        val iosTest by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }
    }
}

//
//kmmbridge {
//  githubReleaseArtifacts()
//  githubReleaseVersions()
//  versionPrefix.set("0.1")
//  spm()
//}
