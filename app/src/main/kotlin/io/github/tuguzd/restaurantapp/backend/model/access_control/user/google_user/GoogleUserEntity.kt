package io.github.tuguzd.restaurantapp.backend.model.access_control.user.google_user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.toData
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "user_google")
class GoogleUserEntity(
    override val id: NanoId = randomNanoId(),
    override val userType: UserType,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_item_id", referencedColumnName = "id")
    override val serviceItem: ServiceItemEntity?,

    override val email: String?,
    @Column(unique = true) override val username: String,
    @Column(unique = true) val googleId: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
) : UserEntity(
    id, userType, serviceItem, email, username,
    imageUri, description, datetimeCreate, datetimeModify,
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

fun GoogleUserEntity.toGoogleData(): GoogleUserData = GoogleUserData(
    id, userType, serviceItem?.toData(), email, username, googleId,
    imageUri, description, datetimeCreate, datetimeModify,
)

fun GoogleUserData.toGoogleEntity(): GoogleUserEntity = GoogleUserEntity(
    id, userType, serviceItem as ServiceItemEntity?, email, username, googleId,
    imageUri, description, datetimeCreate, datetimeModify,
)
