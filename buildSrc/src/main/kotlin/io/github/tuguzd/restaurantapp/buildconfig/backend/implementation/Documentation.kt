package io.github.tuguzd.restaurantapp.buildconfig.backend.implementation

import io.github.tuguzd.restaurantapp.buildconfig.backend.dependency.Spring
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.springDocumentationImplementation() {
    implementation(Spring.Doc.ui)
    implementation(Spring.Doc.kotlin)
    implementation(Spring.Doc.security)
}
