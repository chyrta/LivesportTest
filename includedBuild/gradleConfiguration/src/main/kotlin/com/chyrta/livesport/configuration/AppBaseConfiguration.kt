package com.chyrta.livesport.configuration

import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppBaseConfiguration: Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        plugins.apply(Plugins.application)
        plugins.apply(Plugins.kotlinAndroid)

        val androidExtension = extensions.getByType(AppExtension::class.java)
        with(androidExtension) {
            compileSdkVersion(App.compileSdkVersion)
            defaultConfig {
                minSdk = App.minSdkVersion
                targetSdk = App.targetSdkVersion
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
                vectorDrawables {
                    useSupportLibrary = true
                }
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            packagingOptions {
                resources.excludes.addAll(
                    listOf(
                        "META-INF/licenses/**",
                        "META-INF/AL2.0",
                        "META-INF/LGPL2.1",
                        "META-INF/LICENSE",
                        "META-INF/LICENSE-notice.md",
                        "META-INF/LICENSE.md",
                    ),
                )
            }

            composeOptions.kotlinCompilerExtensionVersion = "1.3.2"
            buildFeatures.compose = true

            target.tasks.withType(KotlinCompile::class.java).configureEach {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }
    }
}
