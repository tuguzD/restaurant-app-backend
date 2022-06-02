package io.github.tuguzd.restaurantapp.backend.model.role_access_control.user

import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderEntity
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.User
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@Entity
@Table(name = "\"user\"")
@Inheritance(strategy = InheritanceType.JOINED)
open class UserEntity(
    @Id override val id: String = randomNanoId(),
    override val type: UserType,

    override val email: String?,
    override val username: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "user", fetch = FetchType.EAGER)
    override val orders: Set<OrderEntity>,
) : User {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as UserEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun UserEntity.toData() = UserData(
    id, type, email, username, imageUri,
    description, datetimeCreate, datetimeModify, orders
)

@Suppress("UNCHECKED_CAST")
fun UserData.toEntity() = UserEntity(
    id, type, email, username, imageUri, description,
    datetimeCreate, datetimeModify, orders as Set<OrderEntity>
)
