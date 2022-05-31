package io.github.tuguzd.restaurantapp.buildconfig.backend.implementation

import io.github.tuguzd.restaurantapp.buildconfig.backend.dependency.H2
import io.github.tuguzd.restaurantapp.buildconfig.backend.dependency.Koin
import io.github.tuguzd.restaurantapp.buildconfig.backend.dependency.Kotlin
import io.github.tuguzd.restaurantapp.buildconfig.backend.dependency.Spring
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin

fun DependencyHandler.debuggingImplementation() {
    implementation(Koin.logger)
    implementation(Kotlin.QualityAssurance.logger)
}

fun DependencyHandler.testingImplementation() {
    testRuntimeOnly(H2.database)
    testImplementation(Spring.Boot.test)
    testImplementation(kotlin(Kotlin.QualityAssurance.test))
}
