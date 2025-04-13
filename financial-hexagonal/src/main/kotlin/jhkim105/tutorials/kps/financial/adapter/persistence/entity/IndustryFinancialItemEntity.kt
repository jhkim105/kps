package jhkim105.tutorials.kps.financial.adapter.persistence.entity

import jakarta.persistence.*
import jhkim105.tutorials.kps.financial.domain.Industry

@Entity
@Table(name = "industry_financial_item")
data class IndustryFinancialItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    val industry: Industry,

    @ManyToOne
    @JoinColumn(name = "financial_item_id")
    val item: FinancialItemEntity
)