package jhkim105.tutorials.kps.financial.entity

import jakarta.persistence.*

@Entity
@Table(name = "industry_financial_item")
data class IndustryFinancialItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "industry_id")
    val industry: Industry,

    @ManyToOne
    @JoinColumn(name = "financial_item_id")
    val item: FinancialItem
)