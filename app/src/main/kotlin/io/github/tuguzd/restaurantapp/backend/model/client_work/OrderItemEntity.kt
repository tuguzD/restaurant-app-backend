package io.github.tuguzd.restaurantapp.backend.model.client_work

import io.github.tuguzd.restaurantapp.backend.model.meal.MenuItemEntity
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItem
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@Entity
@Table(name = "\"order_item\"")
class OrderItemEntity(
    @Id override val id: NanoId = randomNanoId(),

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    override val order: OrderEntity,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
    override val menuItem: MenuItemEntity,

    override val itemCount: Int,
    override val description: String?,

    override val datetimeCreate: String,
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
    description, datetimeCreate, datetimeModify,
)

fun OrderItemData.toEntity() = OrderItemEntity(
    id, order as OrderEntity, menuItem as MenuItemEntity,
    itemCount, description, datetimeCreate, datetimeModify,
)
