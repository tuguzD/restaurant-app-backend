package io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.google_user

import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderEntity
import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

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

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "user", fetch = FetchType.EAGER)
    override val orders: Set<OrderEntity>,
) : UserEntity(
    id, type, email, username, description, imageUri, datetimeCreate, datetimeModify, orders
) {

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
    description, datetimeCreate, datetimeModify, orders
)

@Suppress("UNCHECKED_CAST")
fun GoogleUserData.toGoogleEntity() = GoogleUserEntity(
    id, type, email, username, googleId, imageUri, description,
    datetimeCreate, datetimeModify, orders as Set<OrderEntity>
)
