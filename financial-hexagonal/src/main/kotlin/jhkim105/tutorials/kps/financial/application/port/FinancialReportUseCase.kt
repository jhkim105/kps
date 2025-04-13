package jhkim105.tutorials.kps.financial.application.port

import jhkim105.tutorials.kps.financial.domain.PeriodType

interface FinancialReportUseCase {
    fun getFinancialReport(symbol: String, yearMonth: String, periodType: PeriodType): List<FinancialReportDto>
}