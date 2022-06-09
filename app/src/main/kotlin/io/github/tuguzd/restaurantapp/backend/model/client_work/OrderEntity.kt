package io.github.tuguzd.restaurantapp.backend.model.client_work

import io.github.tuguzd.restaurantapp.backend.model.access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.backend.model.access_control.user.toData
import io.github.tuguzd.restaurantapp.backend.model.access_control.user.toEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemPointEntity
import io.github.tuguzd.restaurantapp.backend.model.organization.toData
import io.github.tuguzd.restaurantapp.backend.model.organization.toEntity
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import org.springframework.data.util.ProxyUtils
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"order\"")
class OrderEntity(
    @Id override val id: NanoId = randomNanoId(),

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    override val creator: UserEntity,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_item_point_id", referencedColumnName = "id")
    override val serviceItemPoint: ServiceItemPointEntity?,

    override val clientCount: Int,
    override val purchased: Boolean,

    override val description: String?,

    override val datetimeCreate: String = Date().toString(),
    override val datetimeModify: String? = null,
) : Order {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as OrderEntity
        return this.id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

fun OrderEntity.toData(): OrderData = OrderData(
    id, creator.toData(), serviceItemPoint?.toData(), clientCount,
    purchased, description, datetimeCreate, datetimeModify,
)

fun OrderData.toEntity(): OrderEntity = OrderEntity(
    id, creator.toEntity(), serviceItemPoint?.toEntity(), clientCount,
    purchased, description, datetimeCreate, datetimeModify,
)
