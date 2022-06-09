package io.github.tuguzd.restaurantapp.backend.controller.meal

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.meal.MenuItemDomainService
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("menu_items")
class MenuItemController(override val service: MenuItemDomainService) :
    EntityController<NanoId, MenuItemData>()
