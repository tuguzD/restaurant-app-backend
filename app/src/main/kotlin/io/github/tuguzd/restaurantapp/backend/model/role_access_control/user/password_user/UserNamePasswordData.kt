package io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.password_user

import io.github.tuguzd.restaurantapp.backend.model.client_work.OrderEntity
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.credential.UserCredentials
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.User
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId

data class UserNamePasswordData(
    override val id: String = randomNanoId(),
    override val type: UserType,

    override val email: String?,
    override val username: String,
    override val password: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,

    override val orders: Set<Order>,
) : User, UserCredentials {

    fun toUser() = UserData(
        id, type, email, username, imageUri,
        description, datetimeCreate, datetimeModify, orders
    )

    @Suppress("UNCHECKED_CAST")
    fun toEntity() = UserNamePasswordEntity(
        id, type, email, username, password, imageUri, description,
        datetimeCreate, datetimeModify, orders as Set<OrderEntity>
    )
}
