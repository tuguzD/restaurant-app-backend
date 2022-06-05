package io.github.tuguzd.restaurantapp.backend.initializer.client_work

import io.github.tuguzd.restaurantapp.backend.initializer.access_control.user.UserInitializer
import io.github.tuguzd.restaurantapp.backend.initializer.meal.MealInitializer
import io.github.tuguzd.restaurantapp.backend.initializer.organization.OrganizationInitializer
import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderEntity
import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderItemEntity

class ClientWorkInitializer {

    class FirstOrganization {

        inner class FineServiceItem(
            user: UserInitializer,
            org: OrganizationInitializer.FirstOrganization.FineServiceItem,
            menu: MealInitializer.FirstOrganization.FineServiceItem,
        ) {
            val firstPointOrder = OrderEntity(
                creator = user.waiterFineFirstUser,
                serviceItemPoint = org.firstItemPoint,
                clientCount = 1, purchased = false, description = null,
            )
            val secondPointOrder = OrderEntity(
                creator = user.waiterFineFirstUser,
                serviceItemPoint = org.secondItemPoint,
                clientCount = 1, purchased = false, description = null,
            )
            val thirdPointOrder = OrderEntity(
                creator = user.waiterFineFirstUser,
                serviceItemPoint = org.thirdItemPoint,
                clientCount = 1, purchased = false, description = null,
            )

            val firstPointOrderItemFirst = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 3, menuItem = menu.firstBreakfastMenuItem,
            )
            val firstPointOrderItemSecond = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 1, menuItem = menu.thirdLunchMenuItem,
            )
            val firstPointOrderItemThird = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 1, menuItem = menu.secondBreakfastMenuItem,
            )

            val secondPointOrderItemFirst = OrderItemEntity(
                order = secondPointOrder, description = null,
                itemCount = 2, menuItem = menu.thirdBreakfastMenuItem,
            )
            val secondPointOrderItemSecond = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 2, menuItem = menu.firstLunchMenuItem,
            )

            val thirdPointOrderItemFirst = OrderItemEntity(
                order = thirdPointOrder, description = null,
                itemCount = 1, menuItem = menu.secondLunchMenuItem,
            )
        }

        inner class BadServiceItem(
            user: UserInitializer,
            org: OrganizationInitializer.FirstOrganization.BadServiceItem,
            menu: MealInitializer.FirstOrganization.BadServiceItem,
        ) {
            val firstPointOrder = OrderEntity(
                creator = user.waiterBadFirstUser,
                serviceItemPoint = org.firstItemPoint,
                clientCount = 1, purchased = false, description = null,
            )
            val secondPointOrder = OrderEntity(
                creator = user.waiterBadFirstUser,
                serviceItemPoint = org.secondItemPoint,
                clientCount = 1, purchased = false, description = null,
            )
            val thirdPointOrder = OrderEntity(
                creator = user.waiterBadFirstUser,
                serviceItemPoint = org.thirdItemPoint,
                clientCount = 1, purchased = false, description = null,
            )

            val firstPointOrderItemFirst = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 2, menuItem = menu.firstBreakfastMenuItem,
            )
            val firstPointOrderItemSecond = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 3, menuItem = menu.thirdLunchMenuItem,
            )
            val firstPointOrderItemThird = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 1, menuItem = menu.secondBreakfastMenuItem,
            )

            val secondPointOrderItemFirst = OrderItemEntity(
                order = secondPointOrder, description = null,
                itemCount = 1, menuItem = menu.thirdBreakfastMenuItem,
            )
            val secondPointOrderItemSecond = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 1, menuItem = menu.firstLunchMenuItem,
            )

            val thirdPointOrderItemFirst = OrderItemEntity(
                order = thirdPointOrder, description = null,
                itemCount = 2, menuItem = menu.secondLunchMenuItem,
            )
        }
    }

    class SecondOrganization {

        inner class FineServiceItem(
            user: UserInitializer,
            org: OrganizationInitializer.SecondOrganization.FineServiceItem,
            menu: MealInitializer.SecondOrganization.FineServiceItem,
        ) {
            val firstPointOrder = OrderEntity(
                creator = user.waiterFineSecondUser,
                serviceItemPoint = org.firstItemPoint,
                clientCount = 1, purchased = false, description = null,
            )
            val secondPointOrder = OrderEntity(
                creator = user.waiterFineSecondUser,
                serviceItemPoint = org.secondItemPoint,
                clientCount = 1, purchased = false, description = null,
            )
            val thirdPointOrder = OrderEntity(
                creator = user.waiterFineSecondUser,
                serviceItemPoint = org.thirdItemPoint,
                clientCount = 1, purchased = false, description = null,
            )

            val firstPointOrderItemFirst = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 2, menuItem = menu.firstBreakfastMenuItem,
            )
            val firstPointOrderItemSecond = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 2, menuItem = menu.thirdLunchMenuItem,
            )
            val firstPointOrderItemThird = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 2, menuItem = menu.secondBreakfastMenuItem,
            )

            val secondPointOrderItemFirst = OrderItemEntity(
                order = secondPointOrder, description = null,
                itemCount = 4, menuItem = menu.thirdBreakfastMenuItem,
            )
            val secondPointOrderItemSecond = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 2, menuItem = menu.firstLunchMenuItem,
            )

            val thirdPointOrderItemFirst = OrderItemEntity(
                order = thirdPointOrder, description = null,
                itemCount = 3, menuItem = menu.secondLunchMenuItem,
            )
        }

        inner class BadServiceItem(
            user: UserInitializer,
            org: OrganizationInitializer.SecondOrganization.BadServiceItem,
            menu: MealInitializer.SecondOrganization.BadServiceItem,
        ) {
            val firstPointOrder = OrderEntity(
                creator = user.waiterBadSecondUser,
                serviceItemPoint = org.firstItemPoint,
                clientCount = 1, purchased = false, description = null,
            )
            val secondPointOrder = OrderEntity(
                creator = user.waiterBadSecondUser,
                serviceItemPoint = org.secondItemPoint,
                clientCount = 1, purchased = false, description = null,
            )
            val thirdPointOrder = OrderEntity(
                creator = user.waiterBadSecondUser,
                serviceItemPoint = org.thirdItemPoint,
                clientCount = 1, purchased = false, description = null,
            )

            val firstPointOrderItemFirst = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 1, menuItem = menu.firstBreakfastMenuItem,
            )
            val firstPointOrderItemSecond = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 2, menuItem = menu.thirdLunchMenuItem,
            )
            val firstPointOrderItemThird = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 1, menuItem = menu.secondBreakfastMenuItem,
            )

            val secondPointOrderItemFirst = OrderItemEntity(
                order = secondPointOrder, description = null,
                itemCount = 1, menuItem = menu.thirdBreakfastMenuItem,
            )
            val secondPointOrderItemSecond = OrderItemEntity(
                order = firstPointOrder, description = null,
                itemCount = 3, menuItem = menu.firstLunchMenuItem,
            )

            val thirdPointOrderItemFirst = OrderItemEntity(
                order = thirdPointOrder, description = null,
                itemCount = 2, menuItem = menu.secondLunchMenuItem,
            )
        }
    }
}
