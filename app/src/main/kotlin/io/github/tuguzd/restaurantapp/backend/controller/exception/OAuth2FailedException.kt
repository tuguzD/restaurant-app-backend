package io.github.tuguzd.restaurantapp.backend.controller.exception

import org.springframework.security.core.AuthenticationException

class OAuth2FailedException(override val message: String) : AuthenticationException(message)
