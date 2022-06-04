package io.github.tuguzd.restaurantapp.backend.model.access_control.user.password_user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderEntity
import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentials
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@Entity
@Table(name = "user_name_password")
@PrimaryKeyJoinColumn(name = "user_id")
class UserNamePasswordEntity(
    override val id: NanoId = randomNanoId(),
    override val type: UserType,

    override val email: String?,
    override val username: String,
    override val password: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String,
    override val datetimeModify: String?,

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "user", fetch = FetchType.EAGER)
    override val orders: Set<OrderEntity>,
) : UserEntity(
    id, type, email, username, description, imageUri, datetimeCreate, datetimeModify, orders
),
    UserCredentials {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as UserNamePasswordEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun UserNamePasswordEntity.toPasswordData() = UserNamePasswordData(
    id, type, email, username, password, imageUri,
    description, datetimeCreate, datetimeModify, orders,
)

@Suppress("UNCHECKED_CAST")
fun UserNamePasswordData.toPasswordEntity() = UserNamePasswordEntity(
    id, type, email, username, password, imageUri, description,
    datetimeCreate, datetimeModify, orders as Set<OrderEntity>,
)
