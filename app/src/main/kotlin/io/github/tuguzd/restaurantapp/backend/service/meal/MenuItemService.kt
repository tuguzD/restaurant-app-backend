package io.github.tuguzd.restaurantapp.backend.service.meal

import io.github.tuguzd.restaurantapp.backend.model.meal.MenuItemEntity
import io.github.tuguzd.restaurantapp.backend.model.meal.toData
import io.github.tuguzd.restaurantapp.backend.model.meal.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.meal.MenuItemJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.repository.util.RepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MenuItemService(private val repository: MenuItemJpaRepository) :
    RepositoryService<String, MenuItemData> {

    override suspend fun readAll(): List<MenuItemData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(MenuItemEntity::toData)

    override suspend fun readById(id: String): MenuItemData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }?.toData()

    override suspend fun save(item: MenuItemData): MenuItemData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }.toData()

    override suspend fun delete(id: String) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }
}
