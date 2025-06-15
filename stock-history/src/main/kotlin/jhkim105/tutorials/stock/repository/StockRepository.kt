package jhkim105.tutorials.stock.repository

import jhkim105.tutorials.stock.model.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {
    fun findByStockCodeAndExchangeCode(stockCode: String, exchangeCode: String): Stock?
}