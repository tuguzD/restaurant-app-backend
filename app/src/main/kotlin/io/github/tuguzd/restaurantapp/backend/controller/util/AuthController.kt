package io.github.tuguzd.restaurantapp.backend.controller.util

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import io.github.tuguzd.restaurantapp.backend.ApplicationConfiguration
import io.github.tuguzd.restaurantapp.backend.controller.util.exception.UserAlreadyExistsException
import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.google_user.GoogleUserData
import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.password_user.UserNamePasswordData
import io.github.tuguzd.restaurantapp.backend.security.JwtUtils
import io.github.tuguzd.restaurantapp.backend.security.UserDetailsService
import io.github.tuguzd.restaurantapp.backend.service.role_access_control.user.GoogleUserService
import io.github.tuguzd.restaurantapp.backend.service.role_access_control.user.UserNamePasswordService
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.credential.UserCredentialsData
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.token.UserTokenData
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Аутентификация", description = "Конечные сетевые точки обращения для аутентификации")
class AuthController(
    private val applicationConfiguration: ApplicationConfiguration,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtils: JwtUtils,
    private val userDetailsService: UserDetailsService,
    private val userNamePasswordService: UserNamePasswordService,
    private val googleUserService: GoogleUserService,
) : KoinComponent {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private val httpTransport: HttpTransport by inject()
    private val jsonFactory: JsonFactory by inject()

    @PostMapping("auth")
    @Operation(summary = "Вход", description = "Вход пользователя по логину и паролю")
    suspend fun auth(
        @RequestBody
        @Parameter(name = "Данные для входа: имя пользователя и пароль")
        credentials: UserCredentialsData,
    ): ResponseEntity<UserTokenData> {
        val username = credentials.username
        val password = credentials.password
        val authentication = UsernamePasswordAuthenticationToken(username, password)
        authenticationManager.authenticate(authentication)

        val userDetails = userDetailsService.loadUserByUsername(username)
        val token = jwtUtils.generateToken(userDetails)
        logger.info { "User with username $username successfully authenticated" }
        val tokenData = UserTokenData(token)
        return ResponseEntity.ok(tokenData)
    }

    protected suspend fun checkUserNotExists(credentials: UserCredentialsData) {
        if (userNamePasswordService.findByUsername(credentials.username) == null) return
        throw UserAlreadyExistsException("User with username ${credentials.username} already exists")
    }

    @PostMapping("register")
    @Operation(summary = "Регистрация", description = "Регистрация нового пользователя по его данным")
    suspend fun register(
        @RequestBody
        @Parameter(name = "Данные пользователя для регистрации")
        credentials: UserCredentialsData,
    ): ResponseEntity<UserTokenData> {
        checkUserNotExists(credentials)

        val registeredUser = UserNamePasswordData(
            // TODO: Change to "unspecified"
            type = UserType.Waiter,
            email = null,
            username = credentials.username,
            password = passwordEncoder.encode(credentials.password),
            imageUri = null,
            description = null,
            datetimeCreate = null,
            datetimeModify = null,
            orders = setOf(),
        )
        userNamePasswordService.save(registeredUser)

        val response = auth(credentials)
        logger.info { "User with username ${credentials.username} successfully registered" }
        return response
    }

    @PostMapping("oauth2/google")
    @Operation(summary = "Google OAuth 2.0", description = "Аутентификация пользователя Google")
    suspend fun googleOAuth2(@RequestBody idToken: UserTokenData): ResponseEntity<UserTokenData> {
        val idTokenString = idToken.accessToken

        val clientSecrets = applicationConfiguration.oauth2.google
        val tokenRequest = GoogleAuthorizationCodeTokenRequest(
            httpTransport,
            jsonFactory,
            clientSecrets.tokenUri,
            clientSecrets.clientId,
            clientSecrets.clientSecret,
            idTokenString,
            "",
        )
        val tokenResponse: GoogleTokenResponse = withContext(Dispatchers.IO) { tokenRequest.execute() }
        val googleIdToken: GoogleIdToken = tokenResponse.parseIdToken()

        val payload: GoogleIdToken.Payload = googleIdToken.payload
        val googleId: String = payload.subject
        val name = payload["name"] as String
        val email: String? = payload.email
        val userId = when (val entityByGoogleId = googleUserService.findByGoogleId(googleId)) {
            null -> randomNanoId()
            else -> entityByGoogleId.id
        }
        val pictureUrl = payload["picture"] as String?
        val entity = GoogleUserData(
            id = userId,
            // TODO: Change to "unspecified"
            type = UserType.Waiter,
            email = email,
            username = name,
            googleId = googleId,
            imageUri = pictureUrl,
            description = null,
            datetimeCreate = null,
            datetimeModify = null,
            orders = setOf(),
        )
        googleUserService.save(entity)
        logger.info { "Google user $name successfully authorized" }

        val userDetails = userDetailsService.loadUserByUsername(name)
        val token = jwtUtils.generateToken(userDetails)
        val tokenData = UserTokenData(token)
        return ResponseEntity.ok(tokenData)
    }
}
