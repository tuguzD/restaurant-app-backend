package io.github.tuguzd.restaurantapp.backend

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * Configuration of the server application.
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "restaurant-app")
data class ApplicationConfiguration(val oauth2: OAuth2, val jwt: Jwt) {
    data class OAuth2(val google: Google) {
        data class Google(
            val clientId: String,
            val tokenUri: String,
            val clientSecret: String,
        )
    }

    data class Jwt(val secretKey: String)
}
