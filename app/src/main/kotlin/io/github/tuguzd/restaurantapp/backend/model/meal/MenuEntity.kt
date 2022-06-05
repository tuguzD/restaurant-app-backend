package io.github.tuguzd.restaurantapp.backend.model.meal

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemEntity
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.Menu
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuType
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "\"menu\"")
class MenuEntity(
    @Id override val id: NanoId = randomNanoId(),
    override val type: MenuType,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    override val creator: UserEntity,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_item_id", referencedColumnName = "id")
    override val serviceItem: ServiceItemEntity,

    override val name: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
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
    id, type, creator, serviceItem, name, imageUri,
    description, datetimeCreate, datetimeModify,
)

fun MenuData.toEntity() = MenuEntity(
    id, type, creator as UserEntity, serviceItem as ServiceItemEntity,
    name, imageUri, description, datetimeCreate, datetimeModify,
)
