@file:Suppress("UnstableApiUsage")

import com.tambi.convention.implementation
import com.tambi.convention.ksp
import com.tambi.convention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            val libs = versionCatalog

            dependencies {
                implementation(libs.findBundle("room"))
                ksp(libs.findLibrary("androidx-room-compiler"))
            }
        }
    }
}
