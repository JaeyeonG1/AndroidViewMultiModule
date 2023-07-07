import com.android.build.api.dsl.ApplicationExtension
import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import com.tambi.convention.implementation
import com.tambi.convention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.extra

class AndroidApplicationFirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
            }

            val libs = versionCatalog

            dependencies {
                add("implementation", platform(libs.findLibrary("firebase.bom").get()))
                implementation(libs.findLibrary("firebase-cloud-messaging"))
                implementation(libs.findLibrary("firebase-analytics"))
                implementation(libs.findLibrary("firebase-crashlytics"))
            }

            extensions.configure<ApplicationExtension> {
                buildTypes {
                    getByName("debug") {
                        // Set Debug build crashlytics mapping report to reduce build time
                        extra["alwaysUpdateBuildId"] = false
                        configure<CrashlyticsExtension> {
                            mappingFileUploadEnabled = false
                        }
                    }
                }
            }
        }
    }
}
