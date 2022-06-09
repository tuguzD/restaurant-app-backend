package io.github.tuguzd.restaurantapp.backend.controller.util

import io.github.tuguzd.restaurantapp.domain.model.util.feature.Identifiable
import io.github.tuguzd.restaurantapp.domain.repository.util.CrudRepositoryService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

abstract class EntityController<I, T : Identifiable<I>> : CrudRepositoryService<I, T> {
    companion object {
        val logger = KotlinLogging.logger {}
    }
    protected abstract val service: CrudRepositoryService<I, T>

    override suspend fun readAll(): List<T> = service.readAll()

    @GetMapping("all")
    suspend fun readAllApi(): ResponseEntity<List<T>> {
        logger.info { "Requested all items from ${service::class.simpleName}" }
        return ResponseEntity.ok(readAll())
    }

    override suspend fun readById(id: I): T? = service.readById(id)

    @GetMapping("id/{id}")
    suspend fun readByIdApi(
        @PathVariable
        id: I
    ): ResponseEntity<T?> {
        logger.info { "Requested item from ${service::class.simpleName} with ID $id" }
        val item = readById(id)
        if (item == null) {
            logger.info {
                "Item from ${service::class.simpleName} " +
                    "with ID $id not found"
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
        logger.info { "Found item from ${service::class.simpleName} with ID $id" }
        return ResponseEntity.ok(item)
    }

    override suspend fun save(item: T): T = service.save(item)

    @PostMapping("save")
    suspend fun saveApi(
        @RequestBody item: T
    ): ResponseEntity<T> {
        val savedItem = save(item)
        logger.info {
            "Saved item from ${service::class.simpleName} " +
                "with ID ${savedItem.id}"
        }
        return ResponseEntity.ok(savedItem)
    }

    override suspend fun delete(id: I) = service.delete(id)

    @DeleteMapping("delete/{id}")
    suspend fun deleteApi(
        @PathVariable
        id: I
    ): ResponseEntity<Unit> {
        logger.info {
            "Requested deletion of item from " +
                "${service::class.simpleName} with ID $id"
        }
        return ResponseEntity.ok(delete(id))
    }

    override suspend fun clear() = service.clear()

    @DeleteMapping("clear")
    suspend fun clearApi(): ResponseEntity<Unit> {
        logger.info {
            "Requested deletion of all items " +
                "from ${service::class.simpleName}"
        }
        return ResponseEntity.ok(clear())
    }
}
