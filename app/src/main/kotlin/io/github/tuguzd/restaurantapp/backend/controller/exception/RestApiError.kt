package io.github.tuguzd.restaurantapp.backend.controller.exception

import kotlinx.serialization.Serializable

@Serializable
data class RestApiError(val message: String)
