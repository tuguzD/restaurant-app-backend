package io.github.tuguzd.restaurantapp.backend.model.access_control.user.google_user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemEntity
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.User
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import java.util.*

data class GoogleUserData(
    override val id: NanoId = randomNanoId(),
    override val userType: UserType,
    override val serviceItem: ServiceItemData?,

    override val email: String?,
    override val username: String,
    val googleId: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
) : User {

    fun toUser(): UserData = UserData(
        id, userType, serviceItem, email, username, imageUri,
        description, datetimeCreate, datetimeModify,
    )

    fun toEntity(): UserEntity = UserEntity(
        id, userType, serviceItem as ServiceItemEntity?, email, username,
        imageUri, description, datetimeCreate, datetimeModify,
    )
}
