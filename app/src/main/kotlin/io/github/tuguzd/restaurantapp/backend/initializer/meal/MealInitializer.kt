package io.github.tuguzd.restaurantapp.backend.initializer.meal

import io.github.tuguzd.restaurantapp.backend.initializer.access_control.user.UserInitializer
import io.github.tuguzd.restaurantapp.backend.initializer.organization.OrganizationInitializer
import io.github.tuguzd.restaurantapp.backend.model.meal.MenuEntity
import io.github.tuguzd.restaurantapp.backend.model.meal.MenuItemEntity
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuType
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemType

class MealInitializer(private val user: UserInitializer) {

    inner class FirstOrganization {

        inner class FineServiceItem(
            org: OrganizationInitializer.FirstOrganization.FineServiceItem
        ) {

            val lunchMenu = MenuEntity(
                creator = user.chefFirstUser,
                type = MenuType.Lunch, name = "Lunch in Fine First service",
                serviceItem = org.item, imageUri = null, description = null,
            )
            val breakfastMenu = MenuEntity(
                creator = user.chefFirstUser,
                type = MenuType.Breakfast, name = "Breakfast in Fine First service",
                serviceItem = org.item, imageUri = null, description = null,
            )

            val firstLunchMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Cocktail, name = "Strawberry cocktail",
                menu = lunchMenu, imageUri = null, description = null,
            )
            val secondLunchMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Dessert, name = "Strawberry ice cream",
                menu = lunchMenu, imageUri = null, description = null,
            )
            val thirdLunchMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Beverage, name = "Good Beer",
                menu = lunchMenu, imageUri = null, description = null,
            )

            val firstBreakfastMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Cold, name = "Meat Salad",
                menu = breakfastMenu, imageUri = null, description = null,
            )
            val secondBreakfastMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Hot, name = "Caesar Salad",
                menu = breakfastMenu, imageUri = null, description = null,
            )
            val thirdBreakfastMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Vegetarian, name = "Fruit Salad",
                menu = breakfastMenu, imageUri = null, description = null,
            )
        }

        inner class BadServiceItem(
            org: OrganizationInitializer.FirstOrganization.BadServiceItem
        ) {

            val lunchMenu = MenuEntity(
                creator = user.chefFirstUser,
                type = MenuType.Lunch, name = "Lunch in Bad First service",
                serviceItem = org.item, imageUri = null, description = null,
            )
            val breakfastMenu = MenuEntity(
                creator = user.chefFirstUser,
                type = MenuType.Breakfast, name = "Breakfast in Bad First service",
                serviceItem = org.item, imageUri = null, description = null,
            )

            val firstLunchMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Dessert, name = "Blackberry ice cream",
                menu = lunchMenu, imageUri = null, description = null,
            )
            val secondLunchMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Cocktail, name = "Blackberry cocktail",
                menu = lunchMenu, imageUri = null, description = null,
            )
            val thirdLunchMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Beverage, name = "Old Vine",
                menu = lunchMenu, imageUri = null, description = null,
            )

            val firstBreakfastMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Vegetarian, name = "Porridge",
                menu = breakfastMenu, imageUri = null, description = null,
            )
            val secondBreakfastMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Hot, name = "Chicken Salad",
                menu = breakfastMenu, imageUri = null, description = null,
            )
            val thirdBreakfastMenuItem = MenuItemEntity(
                creator = user.chefFirstUser,
                type = MenuItemType.Cold, name = "Idk Salad",
                menu = breakfastMenu, imageUri = null, description = null,
            )
        }
    }

    inner class SecondOrganization {

        inner class FineServiceItem(
            org: OrganizationInitializer.SecondOrganization.FineServiceItem
        ) {

            val lunchMenu = MenuEntity(
                creator = user.chefSecondUser,
                type = MenuType.Lunch, name = "Lunch in Fine Second service",
                serviceItem = org.item, imageUri = null, description = null,
            )
            val breakfastMenu = MenuEntity(
                creator = user.chefSecondUser,
                type = MenuType.Breakfast, name = "Breakfast in Fine Second service",
                serviceItem = org.item, imageUri = null, description = null,
            )

            val firstLunchMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Cocktail, name = "Boozy frozen lemonade",
                menu = lunchMenu, imageUri = null, description = null,
            )
            val secondLunchMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Dessert, name = "Creme brulee",
                menu = lunchMenu, imageUri = null, description = null,
            )
            val thirdLunchMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Beverage, name = "Excellent Whisky",
                menu = lunchMenu, imageUri = null, description = null,
            )

            val firstBreakfastMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Cold, name = "Watermelon Poke Bowls",
                menu = breakfastMenu, imageUri = null, description = null,
            )
            val secondBreakfastMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Hot, name = "Cheese burger",
                menu = breakfastMenu, imageUri = null, description = null,
            )
            val thirdBreakfastMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Vegetarian, name = "Potato burger",
                menu = breakfastMenu, imageUri = null, description = null,
            )
        }

        inner class BadServiceItem(
            org: OrganizationInitializer.SecondOrganization.BadServiceItem
        ) {

            val lunchMenu = MenuEntity(
                creator = user.chefSecondUser,
                type = MenuType.Lunch, name = "Lunch in Bad Second service",
                serviceItem = org.item, imageUri = null, description = null,
            )
            val breakfastMenu = MenuEntity(
                creator = user.chefSecondUser,
                type = MenuType.Breakfast, name = "Breakfast in Bad Second service",
                serviceItem = org.item, imageUri = null, description = null,
            )

            val firstLunchMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Dessert, name = "Anzac biscuit tarts",
                menu = lunchMenu, imageUri = null, description = null,
            )
            val secondLunchMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Cocktail, name = "Apple juice",
                menu = lunchMenu, imageUri = null, description = null,
            )
            val thirdLunchMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Beverage, name = "Cheap Vodka",
                menu = lunchMenu, imageUri = null, description = null,
            )

            val firstBreakfastMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Vegetarian, name = "Broccoli cutlet",
                menu = breakfastMenu, imageUri = null, description = null,
            )
            val secondBreakfastMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Hot, name = "Beef steak",
                menu = breakfastMenu, imageUri = null, description = null,
            )
            val thirdBreakfastMenuItem = MenuItemEntity(
                creator = user.chefSecondUser,
                type = MenuItemType.Cold, name = "Sesame Cucumber Noodles",
                menu = breakfastMenu, imageUri = null, description = null,
            )
        }
    }
}
