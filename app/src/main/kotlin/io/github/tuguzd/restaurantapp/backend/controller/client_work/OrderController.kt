package io.github.tuguzd.restaurantapp.backend.controller.client_work

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.client_work.OrderService
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders")
class OrderController(override val service: OrderService) :
    EntityController<String, OrderData>()
