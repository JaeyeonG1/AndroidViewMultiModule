plugins {
    id("tambi.android.library")
    id("tambi.android.library.flavors")
    id("tambi.android.hilt")
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.tambi.core.network"
}

dependencies {

    implementation(libs.bundles.kotlinx)
    testImplementation(libs.kotlinx.coroutines.test)
    implementation(libs.bundles.network)
    implementation(libs.bundles.debugtools)

    implementation(project(":core:model"))
}
