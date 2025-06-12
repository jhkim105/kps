package jhkim105.tutorials.stock.service

import jhkim105.tutorials.stock.model.StockHistoryRepository
import jhkim105.tutorials.stock.model.StockPrice
import jhkim105.tutorials.stock.model.StockPriceRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class StockPriceService(
    private val stockHistoryRepository: StockHistoryRepository,
    private val stockPriceRepository: StockPriceRepository
) {
    fun findPriceByStockIdAndDate(stockId: Long, date: LocalDate): StockPrice? {
        val history = stockHistoryRepository
            .findAll()
            .filter { it.stockId == stockId && it.changeDate <= date }
            .maxByOrNull { it.changeDate }
            ?: return null

        return stockPriceRepository
            .findTopByExchangeCodeAndStockCodeAndBusinessDateLessThanEqualOrderByBusinessDateDesc(
                history.exchangeCode,
                history.stockCode,
                date
            )
    }

    fun findPricesByStockIdAndDate(stockId: Long, date: LocalDate, count: Int): List<StockPrice> {
        val history = stockHistoryRepository
            .findAll()
            .filter { it.stockId == stockId && it.changeDate <= date }
            .maxByOrNull { it.changeDate }
            ?: return emptyList()

        return stockPriceRepository
            .findByExchangeCodeAndStockCodeAndBusinessDateLessThanEqualOrderByBusinessDateDesc(
                history.exchangeCode,
                history.stockCode,
                date
            )
            .take(count)
    }
}
