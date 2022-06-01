package io.github.tuguzd.restaurantapp.backend.model.meal

import io.github.tuguzd.restaurantapp.domain.model.meal.menu.Menu
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItem
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemType
import org.springframework.data.util.ProxyUtils
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"menu_item\"")
class MenuItemEntity(
    @Id override val id: String,
    override val type: MenuItemType,

    override val menu: Menu?,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,
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
    id, type, menu, description,
    imageUri, datetimeCreate, datetimeModify
)

fun MenuItemData.toEntity() = MenuItemEntity(
    id, type, menu, imageUri,
    description, datetimeCreate, datetimeModify
)
