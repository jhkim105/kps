package jhkim105.tutorials.kps.financial.adapter.persistence

import jhkim105.tutorials.kps.financial.adapter.persistence.repository.FinancialReportJpaRepository
import jhkim105.tutorials.kps.financial.adapter.persistence.repository.FinancialValueJpaRepository
import jhkim105.tutorials.kps.financial.application.port.FinancialValuePort
import jhkim105.tutorials.kps.financial.domain.FinancialValue
import jhkim105.tutorials.kps.financial.domain.PeriodType
import org.springframework.stereotype.Component

@Component
class FinancialValuePersistenceAdapter(
    private val financialReportJpaRepository: FinancialReportJpaRepository,
    private val financialValueJpaRepository: FinancialValueJpaRepository
) : FinancialValuePort {
    override fun findFinancialValues(symbol: String, fiscalYearMonth: String, periodType: PeriodType): List<FinancialValue> {
        val report = financialReportJpaRepository.findBySymbolAndFiscalYearMonthAndPeriodType(symbol, fiscalYearMonth, periodType) // symbol에 맞는 FinancialReport을 찾는 메서드 추가 필요
        return if (report != null) {
            financialValueJpaRepository
                .findByFinancialReportId(report.id)
                .map { it.toDomain() }
        } else {
            emptyList()
        }
    }
}