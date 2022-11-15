plugins {
    id("app-configuration")
}

android {
    namespace = "com.chyrta.livesport"
    defaultConfig {
        applicationId = "com.chyrta.livesport"
        minSdk = App.minSdkVersion
        targetSdk = App.targetSdkVersion
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":search:logic"))
    implementation(project(":search:ui"))
}
