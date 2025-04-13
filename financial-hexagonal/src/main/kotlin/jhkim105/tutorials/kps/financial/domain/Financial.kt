package jhkim105.tutorials.kps.financial.domain

import java.math.BigDecimal


@JvmInline
value class YearMonth(private val value: String) {
    init {
        require(Regex("""\d{6}""").matches(value)) {
            "YearMonth must be in the format YYYYMM (e.g. 202312)"
        }
    }
}


data class FinancialItem(
    val id: Long,
    val name: String,
    val depth: Int,
    val parentId: Long?
)

data class FinancialValue(
    val id: Long,
    val reportPeriod: FinancialReportPeriod,
    val value: BigDecimal,
    val financialItem: FinancialItem
)


data class FinancialReportPeriod(
    val id: Long,
    val periodType: PeriodType,
    val fiscalYearMonth: YearMonth
)


enum class PeriodType { YEAR, QUARTER }

enum class Industry { BANKING, IT, MANUFACTURING }