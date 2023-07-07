package com.tambi.convention.configs

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureAndroidBinding(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        dataBinding.enable = true
        viewBinding.enable = true
    }
}
