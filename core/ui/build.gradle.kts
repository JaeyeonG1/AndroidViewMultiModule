plugins {
    id("tambi.android.library")
    id("tambi.android.library.binding")
}

android {
    namespace = "com.tambi.core.ui"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.android.material)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.bundles.kotlinx)
    testImplementation(libs.kotlinx.coroutines.test)
}
