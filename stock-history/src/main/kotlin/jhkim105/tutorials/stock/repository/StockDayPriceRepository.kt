package jhkim105.tutorials.stock.repository

import jhkim105.tutorials.stock.model.StockDayPrice
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface StockDayPriceRepository : JpaRepository<StockDayPrice, Long> {
    fun findByExchangeCodeAndStockCodeAndBusinessDateLessThanEqualOrderByBusinessDateDesc(
        exchangeCode: String,
        stockCode: String,
        date: LocalDate
    ): List<StockDayPrice>
}