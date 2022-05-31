package io.github.tuguzd.restaurantapp.buildconfig.backend.implementation

import io.github.tuguzd.restaurantapp.buildconfig.backend.dependency.Spring
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.springBootImplementation() {
    implementation(Spring.Boot.jpa)
    implementation(Spring.Boot.web)
    implementation(Spring.Boot.security)
    kapt(Spring.Boot.configProcessor)
}
