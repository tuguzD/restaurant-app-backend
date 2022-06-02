package io.github.tuguzd.restaurantapp.backend.controller.meal

import io.github.tuguzd.restaurantapp.backend.controller.util.EntityController
import io.github.tuguzd.restaurantapp.backend.service.meal.MenuService
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("menus")
class MenuController(override val service: MenuService) :
    EntityController<String, MenuData>()
