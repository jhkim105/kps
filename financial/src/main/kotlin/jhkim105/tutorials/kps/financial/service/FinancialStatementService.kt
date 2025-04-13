package jhkim105.tutorials.kps.financial.service

import jhkim105.tutorials.kps.financial.entity.PeriodType
import jhkim105.tutorials.kps.financial.entity.YearMonth
import jhkim105.tutorials.kps.financial.repository.*
import org.springframework.stereotype.Service

@Service
class FinancialStatementService(
    private val financialReportRepository: FinancialReportRepository,
    private val financialValueRepository: FinancialValueRepository,
    private val industryFinancialItemRepository: IndustryFinancialItemRepository,
    private val stockRepository: StockRepository
) {

    fun getFinancialStatement(symbol: String, periodType: PeriodType, period: String): FinancialStatementDto {
        val stock = stockRepository.findBySymbol(symbol)
            ?: throw IllegalArgumentException("Symbol not found: $symbol")

        val fiscalYearMonth = YearMonth(period)
        val reportPeriod = financialReportRepository.findBySymbolAndPeriodTypeAndFiscalYearMonth(symbol, periodType, fiscalYearMonth)
            ?: throw IllegalArgumentException("No report found for $symbol in $periodType $fiscalYearMonth")

        val allowedItems = industryFinancialItemRepository.findByIndustryId(stock.industry.id)
            .map { it.item.id }
            .toSet()

        val values = financialValueRepository.findByFinancialReport(reportPeriod)
            .filter { allowedItems.contains(it.item.id) }
            .sortedWith(compareBy({ it.item.depth }, { it.item.seq }))

        return FinancialStatementDto(
            symbol = symbol,
            industry = stock.industry.name,
            items = values.map {
                FinancialItemDto(
                    itemId = it.item.id,
                    parentItemId = it.item.parent?.id,
                    itemName = it.item.name,
                    value = it.value,
                    depth = it.item.depth
                )
            }
        )
    }
}