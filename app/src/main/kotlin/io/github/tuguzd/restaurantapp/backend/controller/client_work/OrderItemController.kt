package io.github.tuguzd.restaurantapp.backend.controller.client_work

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.client_work.OrderItemDomainService
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("order_items")
class OrderItemController(override val service: OrderItemDomainService) :
    EntityController<NanoId, OrderItemData>()
