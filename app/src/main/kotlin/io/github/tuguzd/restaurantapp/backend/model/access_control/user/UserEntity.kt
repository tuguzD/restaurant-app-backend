package io.github.tuguzd.restaurantapp.backend.model.access_control.user

import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemEntity
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.User
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"user\"")
@Inheritance(strategy = InheritanceType.JOINED)
open class UserEntity(
    @Id override val id: NanoId = randomNanoId(),
    override val type: UserType,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_item_id", referencedColumnName = "id")
    override val serviceItem: ServiceItemEntity?,

    override val email: String?,
    @Column(unique = true) override val username: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
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
    id, type, serviceItem, email, username, imageUri,
    description, datetimeCreate, datetimeModify,
)

fun UserData.toEntity() = UserEntity(
    id, type, serviceItem as ServiceItemEntity?, email, username,
    imageUri, description, datetimeCreate, datetimeModify,
)
