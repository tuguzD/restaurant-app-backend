package io.github.tuguzd.restaurantapp.backend.service.role_access_control.user

import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.google_user.GoogleUserData
import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.google_user.GoogleUserEntity
import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.google_user.toGoogleData
import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.google_user.toGoogleEntity
import io.github.tuguzd.restaurantapp.backend.repository.role_access_control.user.GoogleUserJpaRepository
import io.github.tuguzd.restaurantapp.domain.repository.util.RepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class GoogleUserService(private val repository: GoogleUserJpaRepository) :
    RepositoryService<String, GoogleUserData> {

    override suspend fun readAll(): List<GoogleUserData> =
        withContext(Dispatchers.IO) { repository.findAll() }
            .map(GoogleUserEntity::toGoogleData)

    override suspend fun readById(id: String): GoogleUserData? =
        withContext(Dispatchers.IO) { repository.findByIdOrNull(id) }?.toGoogleData()

    override suspend fun save(item: GoogleUserData): GoogleUserData =
        withContext(Dispatchers.IO) { repository.save(item.toGoogleEntity()) }.toGoogleData()

    override suspend fun delete(id: String) =
        withContext(Dispatchers.IO) { repository.deleteById(id) }

    override suspend fun clear() =
        withContext(Dispatchers.IO) { repository.deleteAll() }

    suspend fun findByGoogleId(googleId: String): GoogleUserData? =
        withContext(Dispatchers.IO) { repository.findByGoogleId(googleId)?.toGoogleData() }
}
