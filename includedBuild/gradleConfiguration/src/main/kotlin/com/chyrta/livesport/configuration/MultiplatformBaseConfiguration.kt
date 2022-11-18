package com.chyrta.livesport.configuration

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class MultiplatformBaseConfiguration : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        plugins.apply(Plugins.library)
        plugins.apply(Plugins.kotlinMultiplatform)
        plugins.apply(Plugins.kotlinSerialization)

        val androidExtension = extensions.getByType(LibraryExtension::class.java)
        with(androidExtension) {
            compileSdk = App.compileSdkVersion
            sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
            defaultConfig {
                minSdk = App.minSdkVersion
                targetSdk = App.targetSdkVersion
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
