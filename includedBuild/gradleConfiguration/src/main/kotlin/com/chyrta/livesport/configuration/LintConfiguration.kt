package com.chyrta.livesport.configuration

import io.gitlab.arturbosch.detekt.CONFIGURATION_DETEKT_PLUGINS
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jlleitschuh.gradle.ktlint.KtlintExtension

class LintConfiguration : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.plugins) {
            apply(Plugins.ktLint)
            apply(Plugins.detekt)
        }

        val ktlintExtension = target.extensions.findByType(KtlintExtension::class.java)
        val detektExtension = target.extensions.findByType(DetektExtension::class.java)
        ktlintExtension?.configure()
        detektExtension?.configure(target)
    }

    private fun KtlintExtension.configure() {
        filter {
            exclude("**/generated/**")
        }
    }

    private fun DetektExtension.configure(target: Project) {
        with(target) {
            toolVersion = "1.21.0"
            source = files("src/main/java")
            config = files("$rootDir/detekt-config.yml")
            parallel = true
            tasks.withType<Detekt>().configureEach {
                reports {
                    html.required.set(true)
                }
            }
            dependencies.add(CONFIGURATION_DETEKT_PLUGINS, Deps.quality.detekt.ktlint)
        }
    }
}
