import com.android.build.api.dsl.LibraryExtension
import com.tambi.convention.configs.configureFlavors
import com.tambi.convention.flavor.AppFlavor
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryFlavorsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                configureFlavors(this, AppFlavor.values().asList())
            }
        }
    }
}
