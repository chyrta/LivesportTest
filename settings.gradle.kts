pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

includeBuild("includedBuild/dependencies")
includeBuild("includedBuild/gradleConfiguration")
include(":androidApp")
include(":core:common")
include(":core:ui")
include(":search:ui")
include(":search:logic")
include(":shared")
rootProject.name = "Livesport"
