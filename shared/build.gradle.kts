plugins {
    id("multiplatform-configuration")
    id("co.touchlab.faktory.kmmbridge") version "0.3.2"
}

kotlin {
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:common"))
                implementation(project(":search:logic"))
            }
        }
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


kmmbridge {
    mavenPublishArtifacts()
    githubReleaseVersions()
    spm()

}
