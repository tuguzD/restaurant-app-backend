package io.github.tuguzd.restaurantapp.backend.controller.organization

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.organization.FoodServiceService
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("services")
class ServiceController(override val service: FoodServiceService) :
    EntityController<String, ServiceData>()
