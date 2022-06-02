package io.github.tuguzd.restaurantapp.backend.model.client_work

import io.github.tuguzd.restaurantapp.backend.model.organization.ServiceItemPointEntity
import io.github.tuguzd.restaurantapp.backend.model.role_access_control.user.UserEntity
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@Entity
@Table(name = "\"order\"")
class OrderEntity(
    @Id override val id: String,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "service_item_point_id", referencedColumnName = "id")
    override val serviceItemPoint: ServiceItemPointEntity?,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    override val user: UserEntity?,

    override val description: String?,

    override val clientCount: Int,
    override val purchased: Boolean,

    override val datetimeCreate: String?,
    override val datetimeModify: String?,

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "order", fetch = FetchType.EAGER)
    override val orderItems: Set<OrderItemEntity>,
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

fun OrderEntity.toData() = OrderData(
    id, user, serviceItemPoint, description, clientCount,
    purchased, datetimeCreate, datetimeModify, orderItems
)

@Suppress("UNCHECKED_CAST")
fun OrderData.toEntity() = OrderEntity(
    id, serviceItemPoint as ServiceItemPointEntity?, user as UserEntity?, description,
    clientCount, purchased, datetimeCreate, datetimeModify, orderItems as Set<OrderItemEntity>
)
