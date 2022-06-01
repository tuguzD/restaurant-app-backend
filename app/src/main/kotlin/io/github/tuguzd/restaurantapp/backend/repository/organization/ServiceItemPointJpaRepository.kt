package io.github.tuguzd.restaurantapp.backend.repository.organization

import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemPointEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceItemPointJpaRepository : JpaRepository<ServiceItemPointEntity, String>