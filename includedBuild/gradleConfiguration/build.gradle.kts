plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("dependencies")
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(Deps.android.plugin)
    implementation(Deps.kotlin.plugin)
    implementation(Deps.kotlinX.serialization.plugin)
    implementation(Deps.kmmBridge.plugin)
    implementation(Deps.quality.ktlint.plugin)
    implementation(Deps.quality.detekt.plugin)
    implementation(Deps.quality.kover.plugin)
    implementation("com.chyrta.livesport.dependencies:dependencies:SNAPSHOT")
}

gradlePlugin {
    plugins {
        register("app-configuration") {
            id = "app-configuration"
            implementationClass = "com.chyrta.livesport.configuration.AppBaseConfiguration"
        }
        register("multiplatform-configuration") {
            id = "multiplatform-configuration"
            implementationClass = "com.chyrta.livesport.configuration.MultiplatformBaseConfiguration"
        }
        register("kmm-bridge-configuration") {
            id = "kmm-bridge-configuration"
            implementationClass = "com.chyrta.livesport.configuration.KmmBridgeConfiguration"
        }
        register("lint-configuration") {
            id = "lint-configuration"
            implementationClass = "com.chyrta.livesport.configuration.LintConfiguration"
        }
        register("library-configuration") {
            id = "library-configuration"
            implementationClass = "com.chyrta.livesport.configuration.LibraryConfiguration"
        }
    }
}
