package io.github.tuguzd.restaurantapp.backend.controller.organization

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.organization.ServiceItemPointDomainService
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("service_item_points")
class ServiceItemPointController(override val service: ServiceItemPointDomainService) :
    EntityController<NanoId, ServiceItemPointData>()
