package jhkim105.tutorials.kps.financial.adapter.persistence.entity

import jakarta.persistence.*
import jhkim105.tutorials.kps.financial.domain.FinancialValue
import java.math.BigDecimal

@Entity
@Table(name = "financial_value")
class FinancialValueEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "financial_report_id")
    val financialReport: FinancialReportEntity,

    @ManyToOne
    @JoinColumn(name = "item_id")
    val item: FinancialItemEntity,

    val value: BigDecimal
) {
    fun toDomain() = FinancialValue(
        id, financialReport.toDomain(), value, item.toDomain()
    )
}