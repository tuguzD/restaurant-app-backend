package io.github.tuguzd.restaurantapp.backend.model.client_work

import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItem
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItem
import org.springframework.data.util.ProxyUtils
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"order_item\"")
class OrderItemEntity(
    @Id override val id: String,
    override val order: Order,
    override val menuItem: MenuItem,

    override val itemCount: Int,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,
) : OrderItem {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as OrderItemEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun OrderItemEntity.toData() = OrderItemData(
    id, order, menuItem, itemCount,
    description, datetimeCreate, datetimeModify
)

fun OrderItemData.toEntity() = OrderItemEntity(
    id, order, menuItem, itemCount,
    description, datetimeCreate, datetimeModify
)
