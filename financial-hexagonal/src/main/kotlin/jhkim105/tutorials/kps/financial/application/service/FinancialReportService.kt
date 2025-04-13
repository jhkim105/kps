package jhkim105.tutorials.kps.financial.application.service

import jhkim105.tutorials.kps.financial.application.port.FinancialReportDto
import jhkim105.tutorials.kps.financial.application.port.FinancialReportUseCase
import jhkim105.tutorials.kps.financial.application.port.FinancialValuePort
import jhkim105.tutorials.kps.financial.domain.PeriodType
import org.springframework.stereotype.Service

@Service
class FinancialReportService(
    private val financialValuePort: FinancialValuePort
) : FinancialReportUseCase {

    override fun getFinancialReport(symbol: String, yearMonth: String, periodType: PeriodType): List<FinancialReportDto> {
        return financialValuePort.findFinancialValues(symbol, yearMonth, periodType)
            .map {
                FinancialReportDto(
                    itemId = it.financialItem.id,
                    parentItemId = it.financialItem.parentId,
                    itemName = it.financialItem.name,
                    value = it.value,
                    depth = it.financialItem.depth
                )
            }
    }
}