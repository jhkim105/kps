package jhkim105.tutorials.kps.financial.entity

import jakarta.persistence.*

@Entity
@Table(name = "financial_report")
class FinancialReport(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val symbol: String,
    @Enumerated(EnumType.STRING)
    val periodType: PeriodType,
    val fiscalYearMonth: YearMonth,     // 기준 년월
)

@JvmInline
value class YearMonth(private val value: String) {
    init {
        require(Regex("""\d{6}""").matches(value)) {
            "YearMonth must be in the format YYYYMM (e.g. 202312)"
        }
    }
}

enum class PeriodType {
    YEAR, QUARTER
}