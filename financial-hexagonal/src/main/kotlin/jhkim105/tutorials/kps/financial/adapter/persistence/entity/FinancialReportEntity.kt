package jhkim105.tutorials.kps.financial.adapter.persistence.entity

import jakarta.persistence.*
import jhkim105.tutorials.kps.financial.domain.FinancialReportPeriod
import jhkim105.tutorials.kps.financial.domain.PeriodType
import jhkim105.tutorials.kps.financial.domain.YearMonth

@Entity
@Table(name = "financial_report")
class FinancialReportEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val symbol: String,
    @Enumerated(EnumType.STRING)
    val periodType: PeriodType,
    val fiscalYearMonth: String,     // 기준 년월
) {
    fun toDomain() = FinancialReportPeriod(
        id = id,
        periodType = periodType,
        fiscalYearMonth = YearMonth(fiscalYearMonth)
    )
}