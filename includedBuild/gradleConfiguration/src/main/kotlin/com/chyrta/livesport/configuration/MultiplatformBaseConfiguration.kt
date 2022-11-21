package com.chyrta.livesport.configuration

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class MultiplatformBaseConfiguration : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        plugins.apply(Plugins.library)
        plugins.apply(Plugins.kotlinMultiplatform)
        plugins.apply(Plugins.kotlinSerialization)
        plugins.apply(Plugins.kover)

        val androidExtension = extensions.getByType(LibraryExtension::class.java)

        with(androidExtension) {
            compileSdk = App.compileSdkVersion
            sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
            defaultConfig {
                minSdk = App.minSdkVersion
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                targetSdk = App.targetSdkVersion
            }

            testOptions {
                unitTests.all {
                    it.useJUnitPlatform()
                }
                unitTests.isReturnDefaultValues = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = "1.3.2"
            }

            tasks.withType(KotlinCompile::class.java).configureEach {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }
    }

}
