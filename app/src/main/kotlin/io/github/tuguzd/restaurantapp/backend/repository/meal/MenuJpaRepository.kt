package io.github.tuguzd.restaurantapp.backend.repository.meal

import io.github.tuguzd.restaurantapp.backend.model.meal.MenuEntity
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuJpaRepository : JpaRepository<MenuEntity, NanoId>
