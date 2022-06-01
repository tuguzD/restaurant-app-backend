package io.github.tuguzd.restaurantapp.backend.security

import io.github.tuguzd.restaurantapp.backend.service.role_access_control.user.UserNamePasswordService
import io.github.tuguzd.restaurantapp.backend.service.role_access_control.user.UserService
import kotlinx.coroutines.runBlocking
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    private val userNamePasswordService: UserNamePasswordService,
    private val userService: UserService,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return when (val user = runBlocking { userNamePasswordService.findByUsername(username) }) {
            null -> {
                val googleUser = runBlocking { userService.findByUsername(username) }
                    ?: throw UsernameNotFoundException("User $username not found")
                User(googleUser.username, "", setOf(SimpleGrantedAuthority("${googleUser.type}")))
            }
            else -> User(user.username, user.password, setOf(SimpleGrantedAuthority("${user.type}")))
        }
    }
}
