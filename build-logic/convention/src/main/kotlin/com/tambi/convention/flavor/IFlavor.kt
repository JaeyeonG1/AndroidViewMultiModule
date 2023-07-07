package com.tambi.convention.flavor

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ProductFlavor

internal interface IFlavor {
    val flavorName: String
    val applicationIdSuffix: String
    val applicationNamePrefix: String
    val applicationVersionNameSuffix: String

    fun getVersionCode(): Int

    fun getVersionName(): String

    fun buildFlavor(
        applicationExtension: ApplicationExtension,
        flavorConfigurationBlock: ProductFlavor.(flavor: AppFlavor) -> Unit
    )
}
