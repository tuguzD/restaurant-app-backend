package io.github.tuguzd.restaurantapp.backend.model.organization

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.backend.model.access_control.user.toData
import io.github.tuguzd.restaurantapp.backend.model.access_control.user.toEntity
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPoint
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "\"service_item_point\"")
class ServiceItemPointEntity(
    @Id override val id: NanoId = randomNanoId(),

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    override val creator: UserEntity,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_item_id", referencedColumnName = "id")
    override val serviceItem: ServiceItemEntity,

    override val name: String,

    override val availability: Boolean,
    override val clientMaxCount: Int,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
) : ServiceItemPoint {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as ServiceItemPointEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun ServiceItemPointEntity.toData(): ServiceItemPointData = ServiceItemPointData(
    id, creator.toData(), serviceItem.toData(), name, availability,
    clientMaxCount, imageUri, description, datetimeCreate, datetimeModify,
)

fun ServiceItemPointData.toEntity(): ServiceItemPointEntity = ServiceItemPointEntity(
    id, creator.toEntity(), serviceItem.toEntity(), name, availability,
    clientMaxCount, imageUri, description, datetimeCreate, datetimeModify,
)
