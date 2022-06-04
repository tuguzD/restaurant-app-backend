package io.github.tuguzd.restaurantapp.backend.controller.organization

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.organization.ServiceItemDomainService
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("service_items")
class ServiceItemController(override val service: ServiceItemDomainService) :
    EntityController<NanoId, ServiceItemData>()
