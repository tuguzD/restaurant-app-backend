package io.github.tuguzd.restaurantapp.backend.model.organization

import io.github.tuguzd.restaurantapp.domain.model.organization.service.Service
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@Entity
@Table(name = "\"service\"")
class ServiceEntity(
    @Id override val id: String,
    override val name: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "service", fetch = FetchType.EAGER)
    override val serviceItems: Set<ServiceItemEntity>,
) : Service {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as ServiceEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun ServiceEntity.toData() = ServiceData(
    id, name, description, imageUri,
    datetimeCreate, datetimeModify, serviceItems
)

@Suppress("UNCHECKED_CAST")
fun ServiceData.toEntity() = ServiceEntity(
    id, name, imageUri, description, datetimeCreate,
    datetimeModify, serviceItems as Set<ServiceItemEntity>
)
