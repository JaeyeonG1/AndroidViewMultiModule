@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.LibraryExtension
import com.tambi.convention.configs.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid()
            }
        }
    }
}
