package jhkim105.tutorials.kps.financial.adapter.persistence.entity

import jakarta.persistence.*
import jhkim105.tutorials.kps.financial.domain.FinancialItem
import jhkim105.tutorials.kps.financial.domain.Industry

@Entity
@Table(name = "financial_item")
class FinancialItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    val seq: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: FinancialItemEntity? = null,

    @OneToMany(mappedBy = "parent")
    val children: List<FinancialItemEntity> = mutableListOf(),

    val depth: Int
) {
    fun toDomain() = FinancialItem(id, name, depth, parent?.id)
}