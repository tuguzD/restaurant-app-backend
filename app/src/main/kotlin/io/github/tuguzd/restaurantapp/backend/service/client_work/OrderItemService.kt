package io.github.tuguzd.restaurantapp.backend.service.client_work

import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderItemEntity
import io.github.tuguzd.restaurantapp.backend.model.client_work.toData
import io.github.tuguzd.restaurantapp.backend.model.client_work.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.client_work.OrderItemJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.domain.repository.util.RepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderItemService(private val repository: OrderItemJpaRepository) :
    RepositoryService<String, OrderItemData> {

    override suspend fun readAll(): List<OrderItemData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(OrderItemEntity::toData)

    override suspend fun readById(id: String): OrderItemData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }?.toData()

    override suspend fun save(item: OrderItemData): OrderItemData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }.toData()

    override suspend fun delete(id: String) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }
}
