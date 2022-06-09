package io.github.tuguzd.restaurantapp.backend.repository.access_control.user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.password_user.UserNamePasswordEntity
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * [JpaRepository] which contains data of type [UserNamePasswordEntity].
 */
@Repository
interface UserNamePasswordJpaRepository : JpaRepository<UserNamePasswordEntity, NanoId> {
    fun readByUsername(username: String): UserNamePasswordEntity?
}
