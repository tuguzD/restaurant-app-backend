package io.github.tuguzd.restaurantapp.backend.initializer

import io.github.tuguzd.restaurantapp.backend.initializer.access_control.user.UserInitializer
import io.github.tuguzd.restaurantapp.backend.initializer.client_work.ClientWorkInitializer
import io.github.tuguzd.restaurantapp.backend.initializer.meal.MealInitializer
import io.github.tuguzd.restaurantapp.backend.initializer.organization.OrganizationInitializer
import io.github.tuguzd.restaurantapp.backend.repository.access_control.user.UserNamePasswordJpaRepository
import io.github.tuguzd.restaurantapp.backend.repository.client_work.OrderItemJpaRepository
import io.github.tuguzd.restaurantapp.backend.repository.client_work.OrderJpaRepository
import io.github.tuguzd.restaurantapp.backend.repository.meal.MenuItemJpaRepository
import io.github.tuguzd.restaurantapp.backend.repository.meal.MenuJpaRepository
import io.github.tuguzd.restaurantapp.backend.repository.organization.ServiceItemJpaRepository
import io.github.tuguzd.restaurantapp.backend.repository.organization.ServiceItemPointJpaRepository
import io.github.tuguzd.restaurantapp.backend.repository.organization.ServiceJpaRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    passwordEncoder: PasswordEncoder,
    private val userRepository: UserNamePasswordJpaRepository,
    private val serviceRepository: ServiceJpaRepository,
    private val serviceItemRepository: ServiceItemJpaRepository,
    private val serviceItemPointRepository: ServiceItemPointJpaRepository,
    private val menuRepository: MenuJpaRepository,
    private val menuItemRepository: MenuItemJpaRepository,
    private val orderRepository: OrderJpaRepository,
    private val orderItemRepository: OrderItemJpaRepository,
) : ApplicationRunner {

    private val user = UserInitializer(passwordEncoder)
    private val org = OrganizationInitializer(user)

    private val firstOrg = org.FirstOrganization()
    private val firstBadOrg = firstOrg.BadServiceItem()
    private val firstFineOrg = firstOrg.FineServiceItem()

    private val secondOrg = org.SecondOrganization()
    private val secondBadOrg = secondOrg.BadServiceItem()
    private val secondFineOrg = secondOrg.FineServiceItem()

    private val meal = MealInitializer(user)

    private val firstOrgBadMenu =
        meal.FirstOrganization().BadServiceItem(firstBadOrg)
    private val firstOrgFineMenu =
        meal.FirstOrganization().FineServiceItem(firstFineOrg)

    private val secondOrgBadMenu =
        meal.SecondOrganization().BadServiceItem(secondBadOrg)
    private val secondOrgFineMenu =
        meal.SecondOrganization().FineServiceItem(secondFineOrg)

    private val firstOrder = ClientWorkInitializer.FirstOrganization()
    private val firstBadOrder =
        firstOrder.BadServiceItem(user, firstBadOrg, firstOrgBadMenu)
    private val firstFineOrder =
        firstOrder.FineServiceItem(user, firstFineOrg, firstOrgFineMenu)

    private val secondOrder = ClientWorkInitializer.SecondOrganization()
    private val secondBadOrder =
        secondOrder.BadServiceItem(user, secondBadOrg, secondOrgBadMenu)
    private val secondFineOrder =
        secondOrder.FineServiceItem(user, secondFineOrg, secondOrgFineMenu)

    override fun run(args: ApplicationArguments?) {
        // Init users
        userRepository.saveAll(
            setOf(
                user.chefFirstUser, user.chefSecondUser,
                user.managerFirstUser, user.managerSecondUser,
                user.lineCookFirstUser, user.lineCookSecondUser,
                user.waiterBadFirstUser, user.waiterBadSecondUser,
                user.waiterFineFirstUser, user.waiterFineSecondUser,
            )
        )

        // Init food services
        serviceRepository.saveAll(setOf(firstOrg.service, secondOrg.service))

        // Init food service items
        serviceItemRepository.saveAll(
            setOf(
                firstFineOrg.item, firstBadOrg.item,
                secondFineOrg.item, secondBadOrg.item,
            )
        )

        // Init food service item points
        serviceItemPointRepository.saveAll(
            setOf(
                firstFineOrg.firstItemPoint, firstBadOrg.firstItemPoint,
                firstFineOrg.secondItemPoint, firstBadOrg.secondItemPoint,
                firstFineOrg.thirdItemPoint, firstBadOrg.thirdItemPoint,

                secondFineOrg.firstItemPoint, secondBadOrg.firstItemPoint,
                secondFineOrg.secondItemPoint, secondBadOrg.secondItemPoint,
                secondFineOrg.thirdItemPoint, secondBadOrg.thirdItemPoint,
            )
        )

        // Init menus
        menuRepository.saveAll(
            setOf(
                firstOrgBadMenu.lunchMenu, firstOrgBadMenu.breakfastMenu,
                firstOrgFineMenu.lunchMenu, firstOrgFineMenu.breakfastMenu,
                secondOrgBadMenu.lunchMenu, secondOrgBadMenu.breakfastMenu,
                secondOrgFineMenu.lunchMenu, secondOrgFineMenu.breakfastMenu,
            )
        )
        // Init menu items
        menuItemRepository.saveAll(
            setOf(
                firstOrgBadMenu.firstBreakfastMenuItem, firstOrgBadMenu.firstLunchMenuItem,
                firstOrgBadMenu.secondBreakfastMenuItem, firstOrgBadMenu.secondLunchMenuItem,
                firstOrgBadMenu.thirdBreakfastMenuItem, firstOrgBadMenu.thirdLunchMenuItem,

                firstOrgFineMenu.firstBreakfastMenuItem, firstOrgFineMenu.firstLunchMenuItem,
                firstOrgFineMenu.secondBreakfastMenuItem, firstOrgFineMenu.secondLunchMenuItem,
                firstOrgFineMenu.thirdBreakfastMenuItem, firstOrgFineMenu.thirdLunchMenuItem,

                secondOrgBadMenu.firstBreakfastMenuItem, secondOrgBadMenu.firstLunchMenuItem,
                secondOrgBadMenu.secondBreakfastMenuItem, secondOrgBadMenu.secondLunchMenuItem,
                secondOrgBadMenu.thirdBreakfastMenuItem, secondOrgBadMenu.thirdLunchMenuItem,

                secondOrgFineMenu.firstBreakfastMenuItem, secondOrgFineMenu.firstLunchMenuItem,
                secondOrgFineMenu.secondBreakfastMenuItem, secondOrgFineMenu.secondLunchMenuItem,
                secondOrgFineMenu.thirdBreakfastMenuItem, secondOrgFineMenu.thirdLunchMenuItem,
            )
        )

        // Init orders
        orderRepository.saveAll(
            setOf(
                firstBadOrder.firstPointOrder, firstFineOrder.firstPointOrder,
                firstBadOrder.secondPointOrder, firstFineOrder.secondPointOrder,
                firstBadOrder.thirdPointOrder, firstFineOrder.thirdPointOrder,

                secondBadOrder.firstPointOrder, secondFineOrder.firstPointOrder,
                secondBadOrder.secondPointOrder, secondFineOrder.secondPointOrder,
                secondBadOrder.thirdPointOrder, secondFineOrder.thirdPointOrder,
            )
        )
        // Init order items
        orderItemRepository.saveAll(
            setOf(
                firstBadOrder.firstPointOrderItemFirst, firstBadOrder.firstPointOrderItemSecond,
                firstBadOrder.firstPointOrderItemThird, firstBadOrder.secondPointOrderItemFirst,
                firstBadOrder.secondPointOrderItemSecond, firstBadOrder.thirdPointOrderItemFirst,

                firstFineOrder.firstPointOrderItemFirst, firstFineOrder.firstPointOrderItemSecond,
                firstFineOrder.firstPointOrderItemThird, firstFineOrder.secondPointOrderItemFirst,
                firstFineOrder.secondPointOrderItemSecond, firstFineOrder.thirdPointOrderItemFirst,

                secondBadOrder.firstPointOrderItemFirst, secondBadOrder.firstPointOrderItemSecond,
                secondBadOrder.firstPointOrderItemThird, secondBadOrder.secondPointOrderItemFirst,
                secondBadOrder.secondPointOrderItemSecond, secondBadOrder.thirdPointOrderItemFirst,

                secondFineOrder.firstPointOrderItemFirst, secondFineOrder.firstPointOrderItemSecond,
                secondFineOrder.firstPointOrderItemThird, secondFineOrder.secondPointOrderItemFirst,
                secondFineOrder.secondPointOrderItemSecond, secondFineOrder.thirdPointOrderItemFirst,
            )
        )
    }
}
