package io.github.tuguzd.restaurantapp.backend.service.meal

import io.github.tuguzd.restaurantapp.backend.model.meal.MenuEntity
import io.github.tuguzd.restaurantapp.backend.model.meal.toData
import io.github.tuguzd.restaurantapp.backend.model.meal.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.meal.MenuJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuData
import io.github.tuguzd.restaurantapp.domain.repository.util.RepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MenuService(private val repository: MenuJpaRepository) :
    RepositoryService<String, MenuData> {

    override suspend fun readAll(): List<MenuData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(MenuEntity::toData)

    override suspend fun readById(id: String): MenuData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }?.toData()

    override suspend fun save(item: MenuData): MenuData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }.toData()

    override suspend fun delete(id: String) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }
}
