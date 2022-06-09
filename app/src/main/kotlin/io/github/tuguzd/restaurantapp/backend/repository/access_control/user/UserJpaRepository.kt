package io.github.tuguzd.restaurantapp.backend.repository.access_control.user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, NanoId> {
    fun readByUsername(username: String): UserEntity?
}
