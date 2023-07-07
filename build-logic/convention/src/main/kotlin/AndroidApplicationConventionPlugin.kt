@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.tambi.convention.configs.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("com.android.application")
            }

            configureKotlinAndroid()

            extensions.configure<BaseAppModuleExtension>() {
                buildTypes {
                    getByName("debug") {
                        isMinifyEnabled = false
                        // Disable abi based multi-apk config to reduce build time
                        splits.abi.isEnable = false
                        // Disable density based multi-apk config to reduce build time
                        splits.density.isEnable = false
                        // Disable PNG Crunching to reduce build time
                        aaptOptions.cruncherEnabled = false
                    }

                    getByName("release") {
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                        // FIXME Minify crashes app
                        isMinifyEnabled = false

                        // Alternative to proguard-android.txt
                        // https://developer.android.com/build/shrink-code
//                        postprocessing {
//                            isRemoveUnusedCode = true
//                            isRemoveUnusedResources = true
//                            isOptimizeCode = true
//                            isObfuscate = true
//                        }
                    }
                }

                // Remove unused variant to reduce build time - prodDebug, devRelease
                // TODO : Enable when staging server ready
                // FIXME : Deprecated
//                variantFilter {
//                    val buildName = buildType.name
//                    val flavorName = flavors[0].name
//
//                    val isIgnoreTargetVariant =
//                        (flavorName == "prod" && buildName == "debug") ||
//                            (flavorName == "dev" && buildName == "release")
//
//                    ignore = isIgnoreTargetVariant
//                }

                // Set output apk file name to each variant
                applicationVariants.all {
                    val variant = this
                    outputs.all { (this as BaseVariantOutputImpl).outputFileName = "ehp-${variant.versionName}.apk" }
                }
            }
        }
    }
}
