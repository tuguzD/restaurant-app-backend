package io.github.tuguzd.restaurantapp.backend.model.meal

import io.github.tuguzd.restaurantapp.domain.model.meal.menu.Menu
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuType
import org.springframework.data.util.ProxyUtils
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"menu\"")
class MenuEntity(
    @Id override val id: String,
    override val type: MenuType,

    override val name: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,
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
    id, type, name, description,
    imageUri, datetimeCreate, datetimeModify
)

fun MenuData.toEntity() = MenuEntity(
    id, type, name, imageUri,
    description, datetimeCreate, datetimeModify
)
