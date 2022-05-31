package io.github.tuguzd.restaurantapp.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestaurantAppBackendApplication

fun main(args: Array<String>) {
    runApplication<RestaurantAppBackendApplication>(*args)
}
