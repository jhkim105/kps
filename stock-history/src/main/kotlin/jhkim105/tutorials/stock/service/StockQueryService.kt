package jhkim105.tutorials.stock.service

import jhkim105.tutorials.stock.model.Stock
import jhkim105.tutorials.stock.repository.StockHistoryRepository
import jhkim105.tutorials.stock.repository.StockRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class StockQueryService(
    private val stockHistoryRepository: StockHistoryRepository,
    private val stockRepository: StockRepository
) {
    fun findStockBy(exchangeCode: String, stockCode: String, date: LocalDate): Stock? {
        val history = stockHistoryRepository
            .findTopByAfterExchangeCodeAndAfterStockCodeAndChangeDateLessThanEqualOrderByChangeDateDesc(
                exchangeCode, stockCode, date
            )
        return history?.let { stockRepository.findByIdOrNull(it.stockId) }
    }
}