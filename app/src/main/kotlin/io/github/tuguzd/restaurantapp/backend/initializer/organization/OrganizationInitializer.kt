package io.github.tuguzd.restaurantapp.backend.initializer.organization

import io.github.tuguzd.restaurantapp.backend.initializer.access_control.user.UserInitializer
import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemPointEntity
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemType

class OrganizationInitializer(private val user: UserInitializer) {

    inner class FirstOrganization {

        val service = ServiceEntity(
            creator = user.managerFirstUser, name = "test_first_service",
            imageUri = null, description = "The very first test food service in system",
        )

        inner class FineServiceItem {

            val item = ServiceItemEntity(
                type = ServiceItemType.Serving, service = service, description = null,
                name = "fine_test_first_service", address = "Moscow Center", imageUri = null,
            )

            val firstItemPoint = ServiceItemPointEntity(
                creator = user.managerFirstUser, serviceItem = item, name = "Table №13",
                availability = false, clientMaxCount = 4, imageUri = null, description = null,
            )
            val secondItemPoint = ServiceItemPointEntity(
                creator = user.managerFirstUser, serviceItem = item, name = "Table №21",
                availability = false, clientMaxCount = 2, imageUri = null, description = null,
            )
            val thirdItemPoint = ServiceItemPointEntity(
                creator = user.managerFirstUser, serviceItem = item, name = "Table №9",
                availability = false, clientMaxCount = 1, imageUri = null, description = null,
            )
        }

        inner class BadServiceItem {

            val item = ServiceItemEntity(
                type = ServiceItemType.Serving, service = service, description = null,
                name = "bad_test_first_service", address = "Muhosransk outskirts", imageUri = null,
            )

            val firstItemPoint = ServiceItemPointEntity(
                creator = user.managerFirstUser, serviceItem = item, name = "Table №2",
                availability = false, clientMaxCount = 2, imageUri = null, description = null,
            )
            val secondItemPoint = ServiceItemPointEntity(
                creator = user.managerFirstUser, serviceItem = item, name = "Table №17",
                availability = false, clientMaxCount = 3, imageUri = null, description = null,
            )
            val thirdItemPoint = ServiceItemPointEntity(
                creator = user.managerFirstUser, serviceItem = item, name = "Table №4",
                availability = false, clientMaxCount = 6, imageUri = null, description = null,
            )
        }
    }

    inner class SecondOrganization {

        val service = ServiceEntity(
            creator = user.managerSecondUser, name = "test_second_service",
            imageUri = null, description = "Sadly, the second test food service in system",
        )

        inner class FineServiceItem {

            val item = ServiceItemEntity(
                type = ServiceItemType.Serving, service = service, description = null,
                name = "fine_test_second_service", address = "Moscow Center", imageUri = null,
            )

            val firstItemPoint = ServiceItemPointEntity(
                creator = user.managerSecondUser, serviceItem = item, name = "Banquet table №4",
                availability = false, clientMaxCount = 12, imageUri = null, description = null,
            )
            val secondItemPoint = ServiceItemPointEntity(
                creator = user.managerSecondUser, serviceItem = item, name = "Table №21",
                availability = false, clientMaxCount = 4, imageUri = null, description = null,
            )
            val thirdItemPoint = ServiceItemPointEntity(
                creator = user.managerSecondUser, serviceItem = item, name = "Table №19",
                availability = false, clientMaxCount = 2, imageUri = null, description = null,
            )
        }

        inner class BadServiceItem {

            val item = ServiceItemEntity(
                type = ServiceItemType.Serving, service = service, description = null,
                name = "bad_test_second_service", address = "Muhosransk outskirts", imageUri = null,
            )

            val firstItemPoint = ServiceItemPointEntity(
                creator = user.managerSecondUser, serviceItem = item, name = "Table №18",
                availability = false, clientMaxCount = 1, imageUri = null, description = null,
            )
            val secondItemPoint = ServiceItemPointEntity(
                creator = user.managerSecondUser, serviceItem = item, name = "Table №1",
                availability = false, clientMaxCount = 2, imageUri = null, description = null,
            )
            val thirdItemPoint = ServiceItemPointEntity(
                creator = user.managerSecondUser, serviceItem = item, name = "Table №11",
                availability = false, clientMaxCount = 3, imageUri = null, description = null,
            )
        }
    }
}
