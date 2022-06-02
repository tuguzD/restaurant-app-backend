package io.github.tuguzd.restaurantapp.backend.model.organization

import io.github.tuguzd.restaurantapp.backend.model.meal.MenuEntity
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItem
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemType
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@Entity
@Table(name = "\"service_item\"")
class ServiceItemEntity(
    @Id override val id: String,

    override val type: ServiceItemType,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    override val service: ServiceEntity?,

    override val name: String,
    override val address: String?,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "serviceItem", fetch = FetchType.EAGER)
    override val menus: Set<MenuEntity>,

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "serviceItem", fetch = FetchType.EAGER)
    override val serviceItemPoints: Set<ServiceItemPointEntity>,
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
    id, type, service, name, description, address, imageUri,
    datetimeCreate, datetimeModify, serviceItemPoints, menus
)

@Suppress("UNCHECKED_CAST")
fun ServiceItemData.toEntity() = ServiceItemEntity(
    id, type, service as ServiceEntity?, name, address, imageUri,
    description, datetimeCreate, datetimeModify, menus as Set<MenuEntity>,
    serviceItemPoints as Set<ServiceItemPointEntity>
)
