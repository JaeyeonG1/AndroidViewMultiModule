@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.LibraryExtension
import com.tambi.convention.configs.configureAndroidBinding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryBindingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("kotlin-kapt")

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidBinding(extension)
        }
    }
}
