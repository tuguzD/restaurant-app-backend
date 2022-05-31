import io.github.tuguzd.restaurantapp.buildconfig.backend.dependency.*
import io.github.tuguzd.restaurantapp.buildconfig.backend.implementation.*

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.jpa")
    kotlin("plugin.spring")
    kotlin("plugin.serialization")
    kotlin("kapt")
}

group = "io.github.tuguzd.restaurantapp.backend"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

dependencies {
    // Kotlin
    kotlinImplementation()

    // Spring
    springBootImplementation()
    runtimeOnly(H2.database)

    // Retrofit
    implementation(Retrofit.core)
    implementation(Retrofit.serializationConverter)

    // Documentation
    springDocumentationImplementation()

    // Dependency injection — Koin
    implementation(Koin.core)

    // Authentication
    implementation(OAuth2.google)

    // Third-Party
    implementation(ThirdParty.jsonWebToken)
    implementation(ThirdParty.jdk15on)

    // Domain layer
    implementation(DomainLayer.dependency) {
        isChanging = true
    }

    // Quality Assurance
    testingImplementation()
    debuggingImplementation()
}

kapt {
    useBuildCache = false
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn"
            )
            jvmTarget = "11"
        }
    }
    named<Jar>("jar") {
        enabled = false
    }
    test {
        useJUnitPlatform()
    }
}
