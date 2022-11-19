package com.chyrta.livesport.configuration

import org.gradle.api.Plugin
import org.gradle.api.Project

class KmmBridgeConfiguration: Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("co.touchlab.faktory.kmmbridge")
    }
}
