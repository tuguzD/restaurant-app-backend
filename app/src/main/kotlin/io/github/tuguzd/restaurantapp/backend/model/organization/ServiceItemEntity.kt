package io.github.tuguzd.restaurantapp.backend.model.organization

import io.github.tuguzd.restaurantapp.domain.model.organization.service.Service
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItem
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemType
import org.springframework.data.util.ProxyUtils
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"service_item\"")
class ServiceItemEntity(
    @Id override val id: String,

    override val type: ServiceItemType,
    override val service: Service?,

    override val name: String,
    override val address: String?,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,
) : ServiceItem {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as ServiceItemEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun ServiceItemEntity.toData() = ServiceItemData(
    id, type, service, name, description,
    address, imageUri, datetimeCreate, datetimeModify
)

fun ServiceItemData.toEntity() = ServiceItemEntity(
    id, type, service, name, address, imageUri,
    description, datetimeCreate, datetimeModify
)
