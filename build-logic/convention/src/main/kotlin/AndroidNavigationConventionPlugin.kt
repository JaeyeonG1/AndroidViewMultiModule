@file:Suppress("UnstableApiUsage")

import com.tambi.convention.implementation
import com.tambi.convention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidNavigationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("androidx.navigation.safeargs.kotlin")

            val libs = versionCatalog

            dependencies {
                implementation(libs.findBundle("navigation"))
            }
        }
    }
}
