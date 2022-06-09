package io.github.tuguzd.restaurantapp.backend.repository.organization

import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceEntity
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceJpaRepository : JpaRepository<ServiceEntity, NanoId>
