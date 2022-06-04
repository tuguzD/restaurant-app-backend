package io.github.tuguzd.restaurantapp.backend.service.organization

import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.toData
import io.github.tuguzd.restaurantapp.backend.model.organization.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.organization.ServiceJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.repository.organization.ServiceDataRepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ServiceDomainService(private val repository: ServiceJpaRepository) :
    ServiceDataRepositoryService {

    override suspend fun readAll(): List<ServiceData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(ServiceEntity::toData)

    override suspend fun readById(id: NanoId): ServiceData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }
            ?.toData()

    override suspend fun save(item: ServiceData): ServiceData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }
            .toData()

    override suspend fun delete(id: NanoId) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }
}
