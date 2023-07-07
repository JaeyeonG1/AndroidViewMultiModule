package com.tambi.convention.flavor

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.ProductFlavor
import com.tambi.convention.Const
import com.tambi.convention.Versions

internal enum class AppFlavor(
    override val flavorName: String,
    override val applicationIdSuffix: String = "",
    override val applicationNamePrefix: String = "",
    override val applicationVersionNameSuffix: String = "",
) : IFlavor {

    DEVELOP(
        flavorName = "dev",
        applicationIdSuffix = ".dev",
        applicationNamePrefix = "(dev)",
        applicationVersionNameSuffix = "-dev."
    ),

    STAGING(
        flavorName = "staging",
        applicationIdSuffix = ".staging",
        applicationNamePrefix = "(stg)",
        applicationVersionNameSuffix = "-stg."
    ),

    PRODUCTION(
        flavorName = "prod"
    );

    override fun getVersionCode() = with(Versions) {
        val versionCodeCommon = MajorVersion * 1000 + MinorVersion * 100 + PatchVersion * 10
        val versionCodeFlavor = when (this@AppFlavor) {
            PRODUCTION -> 0
            STAGING -> StagingVersion
            DEVELOP -> DevelopVersion
        }
        return@with versionCodeCommon + versionCodeFlavor
    }

    override fun getVersionName() = with(Versions) {
        val commonVersionName = "$MajorVersion.$MinorVersion.$PatchVersion"
        val flavorVersionName = when (this@AppFlavor) {
            PRODUCTION -> ""
            STAGING -> "$applicationVersionNameSuffix$StagingVersion"
            DEVELOP -> "$applicationVersionNameSuffix$DevelopVersion"
        }
        return@with commonVersionName + flavorVersionName
    }

    override fun buildFlavor(
        applicationExtension: ApplicationExtension,
        flavorConfigurationBlock: ProductFlavor.(flavor: AppFlavor) -> Unit
    ) {
        applicationExtension.productFlavors {
            create(this@AppFlavor.flavorName) {
                dimension = FlavorDimension.mode.name
                flavorConfigurationBlock(this, this@AppFlavor)

                this.applicationIdSuffix = this@AppFlavor.applicationIdSuffix
                setFlavorResValues(this@AppFlavor)
                setFlavorVersions(this@AppFlavor)
            }
        }
    }

    private fun ApplicationProductFlavor.stringBuildConfigField(name: String, value: String) {
        val prefix = "\""
        val suffix = "\""

        buildConfigField("String", name, "$prefix$value$suffix")
    }

    private fun ApplicationProductFlavor.setFlavorResValues(flavor: AppFlavor) {
        resValue("string", "app_name", "${flavor.applicationNamePrefix}${Const.appName}")
        resValue("string", "deep_link_scheme", "${Const.appNamespace}.app")
        resValue(
            "string",
            "deep_link_host",
            "${Const.appNamespace}${flavor.applicationIdSuffix}.launcher"
        )
    }

    private fun ApplicationProductFlavor.setFlavorVersions(flavor: AppFlavor) {
        versionCode = flavor.getVersionCode()
        versionName = flavor.getVersionName()
    }
}
