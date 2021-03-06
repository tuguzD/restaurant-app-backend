package io.github.tuguzd.restaurantapp.backend.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {
    single<Json> { json() }
}

private fun json() = Json
