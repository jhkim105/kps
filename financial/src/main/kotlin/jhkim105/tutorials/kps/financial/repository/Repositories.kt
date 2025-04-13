package jhkim105.tutorials.kps.financial.repository

import jhkim105.tutorials.kps.financial.entity.*
import org.springframework.data.jpa.repository.JpaRepository

interface FinancialReportRepository : JpaRepository<FinancialReport, Long> {
    fun findBySymbolAndPeriodTypeAndFiscalYearMonth(symbol: String, periodType: PeriodType, fiscalYearMonth: YearMonth): FinancialReport?
}

interface FinancialValueRepository : JpaRepository<FinancialValue, Long> {
    fun findByFinancialReport(financialReport: FinancialReport): List<FinancialValue>
}

interface IndustryFinancialItemRepository : JpaRepository<IndustryFinancialItem, Long> {
    fun findByIndustryId(industryCode: String): List<IndustryFinancialItem>
}

interface IndustryRepository : JpaRepository<Industry, String> {
}

interface StockRepository : JpaRepository<Stock, String> {
    fun findBySymbol(symbol: String): Stock?
}