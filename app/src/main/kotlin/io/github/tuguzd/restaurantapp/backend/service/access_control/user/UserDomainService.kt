package io.github.tuguzd.restaurantapp.backend.service.access_control.user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.backend.model.access_control.user.toData
import io.github.tuguzd.restaurantapp.backend.model.access_control.user.toEntity
import io.github.tuguzd.restaurantapp.backend.repository.access_control.user.UserJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.repository.access_control.UserDataRepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserDomainService(private val repository: UserJpaRepository) :
    UserDataRepositoryService {

    override suspend fun readAll(): List<UserData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(UserEntity::toData)

    override suspend fun readById(id: NanoId): UserData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }
            ?.toData()

    override suspend fun save(item: UserData): UserData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }
            .toData()

    override suspend fun delete(id: NanoId) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }

    override suspend fun readByUsername(username: String): UserData? =
        withContext(Dispatchers.IO) { repository.readByUsername(username) }
            ?.toData()
}
