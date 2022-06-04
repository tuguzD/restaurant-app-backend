package io.github.tuguzd.restaurantapp.backend.service.access_control.user

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.password_user.UserNamePasswordData
import io.github.tuguzd.restaurantapp.backend.model.access_control.user.password_user.UserNamePasswordEntity
import io.github.tuguzd.restaurantapp.backend.model.access_control.user.password_user.toPasswordData
import io.github.tuguzd.restaurantapp.backend.repository.access_control.user.UserNamePasswordJpaRepository
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.repository.util.CrudRepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserNamePasswordDomainService(private val repository: UserNamePasswordJpaRepository) :
    CrudRepositoryService<NanoId, UserNamePasswordData> {

    override suspend fun readAll(): List<UserNamePasswordData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(UserNamePasswordEntity::toPasswordData)

    override suspend fun readById(id: NanoId): UserNamePasswordData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }
            ?.toPasswordData()

    override suspend fun save(item: UserNamePasswordData): UserNamePasswordData =
        withContext(Dispatchers.IO) { repository.save(item.toEntity()) }
            .toPasswordData()

    override suspend fun delete(id: NanoId) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }

    suspend fun findByUsername(username: String): UserNamePasswordData? =
        withContext(Dispatchers.IO) { repository.readByUsername(username) }
            ?.toPasswordData()
}
