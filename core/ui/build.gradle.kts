plugins {
    id("library-configuration")
}

android {
    namespace = "com.chyrta.livesport.ui"
}

dependencies {
    api(project(":core:common"))
    api(Deps.android.androidX.core)
    api(Deps.android.androidX.material)
    api(Deps.android.compose.ui)
    api(Deps.android.compose.constraintLayout)
    api(Deps.android.compose.runtime)
    api(Deps.android.compose.foundation)
    api(Deps.android.compose.navigationCompose)
    api(Deps.android.compose.uiTooling)
    api(Deps.android.compose.uiToolingPreview)
    api(Deps.android.accompanist.accompanistSystemController)
    api(Deps.kiwi.orbit.ui)
    api(Deps.kiwi.orbit.icons)
    api(Deps.kiwi.orbit.illustrations)
}
