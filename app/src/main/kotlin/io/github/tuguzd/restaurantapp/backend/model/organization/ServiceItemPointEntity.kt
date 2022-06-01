package io.github.tuguzd.restaurantapp.backend.model.organization

import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItem
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPoint
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import org.springframework.data.util.ProxyUtils
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"service_item_point\"")
class ServiceItemPointEntity(
    @Id override val id: String,
    override val serviceItem: ServiceItem?,

    override val name: String,

    override val availability: Boolean,
    override val clientMaxCount: Int,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,
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

fun ServiceItemPointEntity.toData() = ServiceItemPointData(
    id, serviceItem, name, description, availability,
    clientMaxCount, imageUri, datetimeCreate, datetimeModify
)

fun ServiceItemPointData.toEntity() = ServiceItemPointEntity(
    id, serviceItem, name, availability, clientMaxCount,
    imageUri, description, datetimeCreate, datetimeModify
)
