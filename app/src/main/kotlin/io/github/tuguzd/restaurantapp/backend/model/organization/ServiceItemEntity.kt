package io.github.tuguzd.restaurantapp.backend.model.organization

import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItem
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemType
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "\"service_item\"")
class ServiceItemEntity(
    @Id override val id: NanoId = randomNanoId(),
    override val serviceItemType: ServiceItemType,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    override val service: ServiceEntity,

    @Column(unique = true) override val name: String,
    override val address: String,

    override val imageUri: String?,
    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
) : ServiceItem {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as ServiceItemEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun ServiceItemEntity.toData(): ServiceItemData = ServiceItemData(
    id, serviceItemType, service.toData(), name, address,
    imageUri, description, datetimeCreate, datetimeModify,
)

fun ServiceItemData.toEntity(): ServiceItemEntity = ServiceItemEntity(
    id, serviceItemType, service.toEntity(), name, address,
    imageUri, description, datetimeCreate, datetimeModify,
)
