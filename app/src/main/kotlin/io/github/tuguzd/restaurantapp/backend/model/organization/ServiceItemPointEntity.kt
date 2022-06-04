package io.github.tuguzd.restaurantapp.backend.model.organization

import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderEntity
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPoint
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@Entity
@Table(name = "\"service_item_point\"")
class ServiceItemPointEntity(
    @Id override val id: NanoId = randomNanoId(),

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_item_id", referencedColumnName = "id")
    override val serviceItem: ServiceItemEntity,

    override val name: String,

    override val availability: Boolean,
    override val clientMaxCount: Int,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String,
    override val datetimeModify: String?,

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "serviceItemPoint", fetch = FetchType.EAGER)
    override val orders: Set<OrderEntity>,
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
    id, serviceItem, name, availability, clientMaxCount,
    imageUri, description, datetimeCreate, datetimeModify, orders,
)

@Suppress("UNCHECKED_CAST")
fun ServiceItemPointData.toEntity() = ServiceItemPointEntity(
    id, serviceItem as ServiceItemEntity, name, availability, clientMaxCount,
    imageUri, description, datetimeCreate, datetimeModify, orders as Set<OrderEntity>,
)
