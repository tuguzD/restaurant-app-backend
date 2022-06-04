package io.github.tuguzd.restaurantapp.backend.service.organization

import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemPointEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.toData
import io.github.tuguzd.restaurantapp.backend.model.organization.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.organization.ServiceItemPointJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.repository.organization.ServiceItemPointDataRepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ServiceItemPointDomainService(private val repository: ServiceItemPointJpaRepository) :
    ServiceItemPointDataRepositoryService {

    override suspend fun readAll(): List<ServiceItemPointData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(ServiceItemPointEntity::toData)

    override suspend fun readById(id: NanoId): ServiceItemPointData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }
            ?.toData()

    override suspend fun save(item: ServiceItemPointData): ServiceItemPointData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }
            .toData()

    override suspend fun delete(id: NanoId) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }
}
