package io.github.tuguzd.restaurantapp.backend.initializer.access_control.user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.password_user.UserNamePasswordEntity
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.util.checkPassword
import io.github.tuguzd.restaurantapp.domain.util.checkUsername
import org.springframework.security.crypto.password.PasswordEncoder

class UserInitializer(private val passwordEncoder: PasswordEncoder) {

    private fun initUser(username: String, password: String, role: UserType): UserNamePasswordEntity {
        require(checkUsername(username) && checkPassword(password))
        return UserNamePasswordEntity(
            type = role, serviceItem = null, email = null, username = username,
            password = passwordEncoder.encode(password), imageUri = null, description = null,
        )
    }

    val managerFirstUser = initUser(
        username = "test_manager_first",
        password = "t3st_manager-PASS", role = UserType.Manager,
    )
    val chefFirstUser = initUser(
        username = "test_chef_first", password = "t3st_chef-PASS", role = UserType.Chef,
    )
    val lineCookFirstUser = initUser(
        username = "test_line_cook_first",
        password = "t3st_line_cook-PASS", role = UserType.LineCook,
    )
    val waiterFineFirstUser = initUser(
        username = "test_waiter_fine_first",
        password = "t3st_waiter-first-PASS", role = UserType.Waiter,
    )
    val waiterBadFirstUser = initUser(
        username = "test_waiter_bad_first",
        password = "t3st_waiter-first-PASS", role = UserType.Waiter,
    )

    val managerSecondUser = initUser(
        username = "test_manager_second",
        password = "t3st_manager-PASS", role = UserType.Manager,
    )
    val chefSecondUser = initUser(
        username = "test_chef_second", password = "t3st_chef-PASS", role = UserType.Chef,
    )
    val lineCookSecondUser = initUser(
        username = "test_line_cook_second",
        password = "t3st_line_cook-PASS", role = UserType.LineCook,
    )
    val waiterFineSecondUser = initUser(
        username = "test_waiter_fine_second",
        password = "t3st_waiter-second-PASS", role = UserType.Waiter,
    )
    val waiterBadSecondUser = initUser(
        username = "test_waiter_bad_second",
        password = "t3st_waiter-second-PASS", role = UserType.Waiter,
    )
}
