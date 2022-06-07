package io.github.tuguzd.restaurantapp.backend.model.access_control.user.password_user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.toData
import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentials
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_name_password")
class UserNamePasswordEntity(
    override val id: NanoId = randomNanoId(),
    override val userType: UserType,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_item_id", referencedColumnName = "id")
    override val serviceItem: ServiceItemEntity?,

    override val email: String?,
    @Column(unique = true) override val username: String,
    override val password: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
) : UserCredentials, UserEntity(
    id, userType, serviceItem, email, username,
    imageUri, description, datetimeCreate, datetimeModify
) {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as UserNamePasswordEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun UserNamePasswordEntity.toPasswordData(): UserNamePasswordData = UserNamePasswordData(
    id, userType, serviceItem?.toData(), email, username, password, imageUri,
    description, datetimeCreate, datetimeModify,
)

fun UserNamePasswordData.toPasswordEntity(): UserNamePasswordEntity = UserNamePasswordEntity(
    id, userType, serviceItem as ServiceItemEntity?, email, username, password,
    imageUri, description, datetimeCreate, datetimeModify,
)
