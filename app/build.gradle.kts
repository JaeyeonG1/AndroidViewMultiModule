plugins {
    id("tambi.android.application")
    id("tambi.android.application.flavors")
    id("tambi.android.application.binding")
    id("tambi.android.hilt")
    id("tambi.android.room")
    id("tambi.android.navigation")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Splash Screen API - App Module Needs
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.android.material)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.bundles.kotlinx)
    testImplementation(libs.kotlinx.coroutines.test)

    implementation(libs.bundles.network)

    implementation(libs.bundles.debugtools)
}

kapt {
    correctErrorTypes = true
}
