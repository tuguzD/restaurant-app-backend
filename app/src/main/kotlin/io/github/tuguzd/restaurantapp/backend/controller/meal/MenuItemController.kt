package io.github.tuguzd.restaurantapp.backend.controller.meal

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.meal.MenuItemService
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("menu_items")
class MenuItemController(override val service: MenuItemService) :
    EntityController<String, MenuItemData>()
