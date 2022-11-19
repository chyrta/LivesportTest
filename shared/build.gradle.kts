plugins {
    id("multiplatform-configuration")
    id("kmm-bridge-configuration")
}

kotlin {
    ios {
        binaries {
            framework {
                baseName = "LivesportKit"
                export(project(":core:common"))
                export(project(":search:logic"))
            }
        }
    }
    iosSimulatorArm64 {
        binaries {
            framework {
                baseName = "LivesportKit"
                export(project(":core:common"))
                export(project(":search:logic"))
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:common"))
                api(project(":search:logic"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosMain by getting
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain
        )
        val iosTest by getting
        val iosSimulatorArm64Test by getting
        iosSimulatorArm64Test.dependsOn(iosTest)
    }
}


kmmbridge {
    githubReleaseArtifacts()
    githubReleaseVersions()
    spm("..")
    versionPrefix.set("0.1")
}
