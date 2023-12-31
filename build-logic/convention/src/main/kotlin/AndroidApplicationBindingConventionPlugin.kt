import com.android.build.api.dsl.ApplicationExtension
import com.tambi.convention.configs.configureAndroidBinding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationBindingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("kotlin-kapt")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidBinding(extension)
        }
    }
}
