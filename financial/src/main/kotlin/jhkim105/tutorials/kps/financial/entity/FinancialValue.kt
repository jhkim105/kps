package jhkim105.tutorials.kps.financial.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "financial_value")
class FinancialValue(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "financial_report_id")
    val financialReport: FinancialReport,

    @ManyToOne
    @JoinColumn(name = "item_id")
    val item: FinancialItem,

    val value: BigDecimal
)