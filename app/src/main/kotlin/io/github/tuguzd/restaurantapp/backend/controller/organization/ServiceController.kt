package io.github.tuguzd.restaurantapp.backend.controller.organization

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.organization.ServiceDomainService
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("services")
class ServiceController(override val service: ServiceDomainService) :
    EntityController<NanoId, ServiceData>()
