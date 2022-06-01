package io.github.tuguzd.restaurantapp.backend.model.client_work

import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPoint
import org.springframework.data.util.ProxyUtils
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"order\"")
class OrderEntity(
    @Id override val id: String,

    override val serviceItemPoint: ServiceItemPoint?,
    override val description: String?,

    override val clientCount: Int,
    override val purchased: Boolean,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,
) : Order {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as OrderEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun OrderEntity.toData() = OrderData(
    id, serviceItemPoint, description, clientCount,
    purchased, datetimeCreate, datetimeModify
)

fun OrderData.toEntity() = OrderEntity(
    id, serviceItemPoint, description,
    clientCount, purchased, datetimeCreate, datetimeModify
)
