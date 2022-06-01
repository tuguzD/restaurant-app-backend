package io.github.tuguzd.restaurantapp.backend.repository.client_work

import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemJpaRepository : JpaRepository<OrderItemEntity, String>
