plugins {
    id("tambi.android.library")
    id("tambi.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.tambi.core.storage"
}

dependencies {

    implementation(libs.bundles.kotlinx)
    testImplementation(libs.kotlinx.coroutines.test)
    implementation(libs.androidx.datastore.preference)
    implementation(libs.bundles.debugtools)

    implementation(project(":core:model"))
}
