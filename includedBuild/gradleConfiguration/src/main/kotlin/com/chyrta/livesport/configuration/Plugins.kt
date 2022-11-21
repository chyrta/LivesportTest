package com.chyrta.livesport.configuration

object Plugins {
    val application by lazy { "com.android.application" }
    val library by lazy { "com.android.library" }
    val kotlinAndroid by lazy { "org.jetbrains.kotlin.android"}
    val kotlinSerialization by lazy { "org.jetbrains.kotlin.plugin.serialization" }
    val kotlinMultiplatform by lazy { "org.jetbrains.kotlin.multiplatform" }
    val libraryConfiguration by lazy { "library-configuration" }
    val applicationConfiguration by lazy { "application-configuration" }
    val kmmBridgeConfiguration by lazy { "kmm-bridge-configuration" }
    val ksp by lazy { "com.google.devtools.ksp" }
    val kover by lazy { "org.jetbrains.kotlinx.kover" }
}
