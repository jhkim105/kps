package jhkim105.tutorials.stock.repository

import jhkim105.tutorials.stock.model.StockHistory
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface StockHistoryRepository : JpaRepository<StockHistory, Long> {
    fun findTopByAfterExchangeCodeAndAfterStockCodeAndChangeDateLessThanEqualOrderByChangeDateDesc(
        exchangeCode: String,
        stockCode: String,
        date: LocalDate
    ): StockHistory?

    fun findTopByStockIdAndChangeDateLessThanEqualOrderByChangeDateDesc(
        stockId: Long,
        date: LocalDate
    ): StockHistory?

    fun findByStockIdAndChangeDateLessThanEqual(
        stockId: Long,
        date: LocalDate
    ): List<StockHistory>
}