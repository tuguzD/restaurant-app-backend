package io.github.tuguzd.restaurantapp.backend.model.meal

import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemEntity
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.Menu
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuType
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@Entity
@Table(name = "\"menu\"")
class MenuEntity(
    @Id override val id: NanoId = randomNanoId(),
    override val type: MenuType,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_item_id", referencedColumnName = "id")
    override val serviceItem: ServiceItemEntity,

    override val name: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String,
    override val datetimeModify: String?,

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "menu", fetch = FetchType.EAGER)
    override val menuItems: Set<MenuItemEntity>,
) : Menu {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as MenuEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun MenuEntity.toData() = MenuData(
    id, type, serviceItem, name, imageUri,
    description, datetimeCreate, datetimeModify, menuItems,
)

@Suppress("UNCHECKED_CAST")
fun MenuData.toEntity() = MenuEntity(
    id, type, serviceItem as ServiceItemEntity, name, imageUri, description,
    datetimeCreate, datetimeModify, menuItems as Set<MenuItemEntity>,
)
