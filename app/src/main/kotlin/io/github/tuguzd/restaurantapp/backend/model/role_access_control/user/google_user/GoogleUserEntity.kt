package io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.google_user

import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity
@Table(name = "user_google")
@PrimaryKeyJoinColumn(name = "user_id")
class GoogleUserEntity(
    override val id: String = randomNanoId(),
    override val type: UserType,

    override val email: String?,
    override val username: String,
    @Column(unique = true) val googleId: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,
) : UserEntity(id, type, email, username, description, imageUri, datetimeCreate, datetimeModify) {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as GoogleUserEntity
        return this.id == other.id
    }

    override fun hashCode() = javaClass.hashCode()
}

fun GoogleUserEntity.toGoogleData() = GoogleUserData(
    id, type, email, username, googleId, imageUri,
    description, datetimeCreate, datetimeModify
)

fun GoogleUserData.toGoogleEntity() = GoogleUserEntity(
    id, type, email, username, googleId, imageUri,
    description, datetimeCreate, datetimeModify
)
