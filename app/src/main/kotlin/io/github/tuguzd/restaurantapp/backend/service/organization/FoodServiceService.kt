package io.github.tuguzd.restaurantapp.backend.service.organization

import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.toData
import io.github.tuguzd.restaurantapp.backend.model.organization.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.organization.ServiceJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import io.github.tuguzd.restaurantapp.domain.repository.util.RepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FoodServiceService(private val repository: ServiceJpaRepository) :
    RepositoryService<String, ServiceData> {

    override suspend fun readAll(): List<ServiceData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(ServiceEntity::toData)

    override suspend fun readById(id: String): ServiceData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }?.toData()

    override suspend fun save(item: ServiceData): ServiceData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }.toData()

    override suspend fun delete(id: String) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }
}
