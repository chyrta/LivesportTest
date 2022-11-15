package com.chyrta.livesport.configuration

object Plugins {
    val application by lazy { "com.android.application" }
    val library by lazy { "com.android.library" }
    val kotlinAndroid by lazy { "org.jetbrains.kotlin.android"}
    val kotlinSerialization by lazy { "org.jetbrains.kotlin.plugin.serialization" }
    val kotlinMultiplatform by lazy { "org.jetbrains.kotlin.multiplatform" }

    val libraryConfiguration by lazy { "library-configuration" }
}
