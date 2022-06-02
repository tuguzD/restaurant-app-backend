package io.github.tuguzd.restaurantapp.backend.model.meal

import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderItemEntity
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItem
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemType
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@Entity
@Table(name = "\"menu_item\"")
class MenuItemEntity(
    @Id override val id: String,
    override val type: MenuItemType,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    override val menu: MenuEntity?,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "menuItem", fetch = FetchType.EAGER)
    override val orderItems: Set<OrderItemEntity>,
) : MenuItem {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as MenuItemEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun MenuItemEntity.toData() = MenuItemData(
    id, type, menu, description, imageUri,
    datetimeCreate, datetimeModify, orderItems
)

@Suppress("UNCHECKED_CAST")
fun MenuItemData.toEntity() = MenuItemEntity(
    id, type, menu as MenuEntity?, imageUri, description,
    datetimeCreate, datetimeModify, orderItems as Set<OrderItemEntity>
)
