package io.github.tuguzd.restaurantapp.backend.service.role_access_control.user

import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.toData
import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.role_access_control.user.UserJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.repository.util.RepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserJpaRepository) :
    RepositoryService<String, UserData> {

    override suspend fun readAll(): List<UserData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(UserEntity::toData)

    override suspend fun readById(id: String): UserData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }?.toData()

    override suspend fun save(item: UserData): UserData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }.toData()

    override suspend fun delete(id: String) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }

    suspend fun findByUsername(username: String): UserData? =
        withContext(Dispatchers.IO) { repository.findByUsername(username) }?.toData()
}
