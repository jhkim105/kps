package jhkim105.tutorials.kps.financial.adapter.persistence.repository

import jhkim105.tutorials.kps.financial.adapter.persistence.entity.*
import jhkim105.tutorials.kps.financial.domain.Industry
import jhkim105.tutorials.kps.financial.domain.PeriodType
import jhkim105.tutorials.kps.financial.domain.YearMonth
import org.springframework.data.jpa.repository.JpaRepository


interface FinancialReportJpaRepository : JpaRepository<FinancialReportEntity, Long> {
    fun findBySymbolAndFiscalYearMonthAndPeriodType(symbol: String, fiscalYearMonth: String, periodType: PeriodType): FinancialReportEntity?
}

interface FinancialValueJpaRepository : JpaRepository<FinancialValueEntity, Long> {
    fun findByFinancialReportId(reportId: Long): List<FinancialValueEntity>
}
