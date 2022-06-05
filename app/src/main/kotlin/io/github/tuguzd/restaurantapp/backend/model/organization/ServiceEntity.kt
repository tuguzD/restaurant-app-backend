package io.github.tuguzd.restaurantapp.backend.model.organization

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.domain.model.organization.service.Service
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "\"service\"")
class ServiceEntity(
    @Id override val id: NanoId = randomNanoId(),

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    override val creator: UserEntity,

    @Column(unique = true) override val name: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
) : Service {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as ServiceEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun ServiceEntity.toData() = ServiceData(
    id, creator, name, imageUri,
    description, datetimeCreate, datetimeModify,
)

fun ServiceData.toEntity() = ServiceEntity(
    id, creator as UserEntity, name,
    imageUri, description, datetimeCreate, datetimeModify,
)
