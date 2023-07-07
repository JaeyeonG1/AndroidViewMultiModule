package com.tambi.convention

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider
import java.util.Optional

internal fun <T> DependencyHandler.implementation(
    dependencyNotation: Optional<Provider<T>>
): Dependency? = add("implementation", dependencyNotation.get())

internal fun <T> DependencyHandler.debugImplementation(
    dependencyNotation: Optional<Provider<T>>
): Dependency? = add("debugImplementation", dependencyNotation.get())

internal fun <T> DependencyHandler.androidTestImplementation(
    dependencyNotation: Optional<Provider<T>>
): Dependency? = add("androidTestImplementation", dependencyNotation.get())

internal fun <T> DependencyHandler.kapt(
    dependencyNotation: Optional<Provider<T>>
): Dependency? = add("kapt", dependencyNotation.get())

internal fun <T> DependencyHandler.kaptAndroidTest(
    dependencyNotation: Optional<Provider<T>>
): Dependency? = add("kaptAndroidTest", dependencyNotation.get())

internal fun <T> DependencyHandler.ksp(
    dependencyNotation: Optional<Provider<T>>
): Dependency? = add("ksp", dependencyNotation.get())

internal fun <T> DependencyHandler.api(
    dependencyNotation: Optional<Provider<T>>
): Dependency? = add("api", dependencyNotation)

internal fun <T> DependencyHandler.coreLibraryDesugar(
    dependencyNotation: Optional<Provider<T>>
): Dependency? = add("coreLibraryDesugaring", dependencyNotation.get())
