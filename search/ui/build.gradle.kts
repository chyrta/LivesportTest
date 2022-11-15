plugins {
    id("library-configuration")
}

android {
    namespace = "com.chyrta.livesport.search.ui"
}

dependencies {
    implementation(project(":search:logic"))
    implementation(project(":core:ui"))
}
