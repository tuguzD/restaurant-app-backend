package io.github.tuguzd.restaurantapp.backend.controller.exception

import org.springframework.security.core.AuthenticationException

class UserAlreadyExistsException(override val message: String) : AuthenticationException(message)
