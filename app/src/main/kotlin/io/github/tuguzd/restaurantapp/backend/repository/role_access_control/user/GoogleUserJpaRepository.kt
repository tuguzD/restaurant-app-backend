package io.github.tuguzd.restaurantapp.backend.repository.role_access_control.user

import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.google_user.GoogleUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * [JpaRepository] which contains data of type [GoogleUserEntity].
 */
@Repository
interface GoogleUserJpaRepository : JpaRepository<GoogleUserEntity, String> {
    fun findByGoogleId(googleId: String): GoogleUserEntity?
}
