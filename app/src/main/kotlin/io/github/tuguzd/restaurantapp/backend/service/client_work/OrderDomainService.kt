package io.github.tuguzd.restaurantapp.backend.service.client_work

import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderEntity
import io.github.tuguzd.restaurantapp.backend.model.client_work.toData
import io.github.tuguzd.restaurantapp.backend.model.client_work.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.client_work.OrderJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.repository.client_work.OrderDataRepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderDomainService(private val repository: OrderJpaRepository) :
    OrderDataRepositoryService {

    override suspend fun readAll(): List<OrderData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(OrderEntity::toData)

    override suspend fun readById(id: NanoId): OrderData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }?.toData()

    override suspend fun save(item: OrderData): OrderData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }.toData()

    override suspend fun delete(id: NanoId) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }
}
