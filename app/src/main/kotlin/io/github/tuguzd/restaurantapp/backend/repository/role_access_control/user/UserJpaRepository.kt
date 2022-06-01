package io.github.tuguzd.restaurantapp.backend.repository.role_access_control.user

import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, String> {
    fun findByUsername(username: String): UserEntity?
}
