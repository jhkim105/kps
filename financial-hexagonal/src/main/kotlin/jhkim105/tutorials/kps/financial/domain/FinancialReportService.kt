package jhkim105.tutorials.kps.financial.domain

interface FinancialReportService {
    fun getReport(symbol: String, year: Int, periodType: PeriodType): List<FinancialValue>
}
