package jhkim105.tutorials.kps.financial.application.port

import jhkim105.tutorials.kps.financial.domain.FinancialValue
import jhkim105.tutorials.kps.financial.domain.PeriodType

interface FinancialValuePort {
    fun findFinancialValues(symbol: String, fiscalYearMonth: String, periodType: PeriodType): List<FinancialValue>
}