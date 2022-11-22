package com.chyrta.livesport.configuration

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class LibraryConfiguration : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        plugins.apply(Plugins.library)
        plugins.apply(Plugins.kotlinAndroid)

        val libraryExtension = extensions.getByType(LibraryExtension::class.java)
        with(libraryExtension) {
            compileSdk = App.compileSdkVersion
            defaultConfig {
                minSdk = App.minSdkVersion
                targetSdk = App.targetSdkVersion
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro",
                    )
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            composeOptions.kotlinCompilerExtensionVersion = Deps.android.composeCompilerVersion
            buildFeatures.compose = true

            tasks.withType(KotlinCompile::class.java).configureEach {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_1_8.toString()
                }
            }
        }
    }

}
