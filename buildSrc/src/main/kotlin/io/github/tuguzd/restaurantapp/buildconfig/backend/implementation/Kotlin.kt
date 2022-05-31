package io.github.tuguzd.restaurantapp.buildconfig.backend.implementation

import io.github.tuguzd.restaurantapp.buildconfig.backend.dependency.Kotlin
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin

fun DependencyHandler.kotlinImplementation() {
    kotlinBaseImplementation()
    kotlinXImplementation()
}

private fun DependencyHandler.kotlinBaseImplementation() {
    implementation(kotlin(Kotlin.reflect))
    implementation(kotlin(Kotlin.stdlib))
}

private fun DependencyHandler.kotlinXImplementation() {
    implementation(Kotlin.X.coroutine)
    implementation(Kotlin.X.reactor)
}
