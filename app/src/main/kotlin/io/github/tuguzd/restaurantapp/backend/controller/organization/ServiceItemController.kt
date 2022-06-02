package io.github.tuguzd.restaurantapp.backend.controller.organization

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.organization.FoodServiceItemService
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("service_items")
class ServiceItemController(override val service: FoodServiceItemService) :
    EntityController<String, ServiceItemData>()
