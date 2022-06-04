package io.github.tuguzd.restaurantapp.backend.repository.access_control.user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.google_user.GoogleUserEntity
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * [JpaRepository] which contains data of type [GoogleUserEntity].
 */
@Repository
interface GoogleUserJpaRepository : JpaRepository<GoogleUserEntity, NanoId> {
    fun readByGoogleId(googleId: String): GoogleUserEntity?
}
