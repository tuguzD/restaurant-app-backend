package io.github.tuguzd.restaurantapp.backend.model.meal

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItem
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemType
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"menu_item\"")
class MenuItemEntity(
    @Id override val id: NanoId = randomNanoId(),
    override val type: MenuItemType,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    override val menu: MenuEntity,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    override val creator: UserEntity,

    override val name: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
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
    id, type, menu, creator, name,
    imageUri, description, datetimeCreate, datetimeModify,
)

fun MenuItemData.toEntity() = MenuItemEntity(
    id, type, menu as MenuEntity, creator as UserEntity, name,
    imageUri, description, datetimeCreate, datetimeModify,
)
