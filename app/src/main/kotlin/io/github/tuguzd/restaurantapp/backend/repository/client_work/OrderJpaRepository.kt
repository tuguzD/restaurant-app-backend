package io.github.tuguzd.restaurantapp.backend.repository.client_work

import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderJpaRepository : JpaRepository<OrderEntity, String>
