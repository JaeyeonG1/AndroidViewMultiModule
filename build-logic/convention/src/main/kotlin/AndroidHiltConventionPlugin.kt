import com.tambi.convention.implementation
import com.tambi.convention.kapt
import com.tambi.convention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Declare needed plugins for custom plugin - Hilt needs kotlin-kapt, dagger.hilt.android.plugin
            with(pluginManager) {
                apply("kotlin-kapt")
                apply("dagger.hilt.android.plugin")
            }

            val libs = versionCatalog

            // Declare needed libraries for custom plugin
            dependencies {
                implementation(libs.findLibrary("hilt-android"))
                implementation(libs.findLibrary("hilt-navigation-fragment"))
                kapt(libs.findLibrary("hilt-compiler"))
                kapt(libs.findLibrary("hilt-android-compiler"))
            }
        }
    }
}
