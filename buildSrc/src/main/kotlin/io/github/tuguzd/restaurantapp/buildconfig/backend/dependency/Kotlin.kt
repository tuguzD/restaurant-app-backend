package io.github.tuguzd.restaurantapp.buildconfig.backend.dependency

object Kotlin {
    private const val version = "1.6.21"

    const val reflect = "reflect"
    const val stdlib = "stdlib"

    object QualityAssurance {
        const val test = "test"
        const val logger = "io.github.microutils:kotlin-logging-jvm:2.1.23"
    }

    object X {
        private const val version = "1.6.2"

        const val coroutine =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val reactor =
            "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$version"
    }
}
