plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

group = "com.chyrta.livesport.dependencies"
version = "SNAPSHOT"

gradlePlugin {
    plugins {
        register("dependencies") {
            id = "dependencies"
            implementationClass = "com.chyrta.livesport.dependencies.DependenciesPlugin"
        }
    }
}
