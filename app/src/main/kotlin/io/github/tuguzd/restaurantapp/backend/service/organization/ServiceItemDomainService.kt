package io.github.tuguzd.restaurantapp.backend.service.organization

import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.toData
import io.github.tuguzd.restaurantapp.backend.model.organization.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.organization.ServiceItemJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.repository.organization.ServiceItemDataRepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ServiceItemDomainService(private val repository: ServiceItemJpaRepository) :
    ServiceItemDataRepositoryService {

    override suspend fun readAll(): List<ServiceItemData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(ServiceItemEntity::toData)

    override suspend fun readById(id: NanoId): ServiceItemData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }
            ?.toData()

    override suspend fun save(item: ServiceItemData): ServiceItemData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }
            .toData()

    override suspend fun delete(id: NanoId) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }
}
