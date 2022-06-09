package io.github.tuguzd.restaurantapp.backend.controller.util.exception

import kotlinx.serialization.Serializable

@Serializable
data class RestApiError(val message: String)
