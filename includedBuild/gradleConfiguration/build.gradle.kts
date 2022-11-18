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
        register("library-configuration") {
            id = "library-configuration"
            implementationClass = "com.chyrta.livesport.configuration.LibraryConfiguration"
        }
    }
}
