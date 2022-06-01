package io.github.tuguzd.restaurantapp.backend.repository.role_access_control.user

import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.password_user.UserNamePasswordEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * [JpaRepository] which contains data of type [UserNamePasswordEntity].
 */
@Repository
interface UserNamePasswordJpaRepository : JpaRepository<UserNamePasswordEntity, String> {
    fun findByUsername(username: String): UserNamePasswordEntity?
}
