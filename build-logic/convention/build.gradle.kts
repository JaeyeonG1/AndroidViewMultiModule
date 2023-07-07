import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.tambi.view-multi-module.buildlogic"

// Match sourceCompatibility & targetCompatibility with AGP version
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Match jvmTarget with AGP version
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

// Dependencies for convention and build-logic
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

// Custom gradle plugin field
gradlePlugin {
    /**
     * Register Plugin in plugins block like Sample below
     *
     * register("androidApplication"){
     *      id = "tambi.android.application" // Id to use in build.gradle.kts
     *      implementationClass = "AndroidApplicationConventionPlugin" // Implemented class which extends Plugin<Project>
     * }
     */
    plugins {
        register("androidApplication") {
            id = "tambi.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationBinding") {
            id = "tambi.android.application.binding"
            implementationClass = "AndroidApplicationBindingConventionPlugin"
        }
        register("androidLibrary") {
            id = "tambi.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryBinding") {
            id = "tambi.android.library.binding"
            implementationClass = "AndroidLibraryBindingConventionPlugin"
        }
        register("androidFirebase") {
            id = "tambi.android.application.firebase"
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }
        register("androidFlavors") {
            id = "tambi.android.application.flavors"
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }
        register("androidHilt") {
            id = "tambi.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidNavigation") {
            id = "tambi.android.navigation"
            implementationClass = "AndroidNavigationConventionPlugin"
        }
        register("androidRoom") {
            id = "tambi.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = "tambi.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}
