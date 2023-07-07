@file:Suppress("UnstableApiUsage")

package com.tambi.convention.configs

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import com.tambi.convention.flavor.FlavorDimension
import com.tambi.convention.flavor.IFlavor

internal fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *>,
    flavors: List<IFlavor>,
    flavorConfigurationBlock: ProductFlavor.(flavor: IFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.mode.name

        if (this is ApplicationExtension) {
            flavors.forEach { it.buildFlavor(this, flavorConfigurationBlock) }
        }
    }
}
