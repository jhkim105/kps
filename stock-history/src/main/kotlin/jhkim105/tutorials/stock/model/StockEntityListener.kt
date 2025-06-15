package jhkim105.tutorials.stock.model

import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import jakarta.persistence.PreUpdate
import jhkim105.tutorials.stock.repository.StockHistoryRepository
import org.springframework.context.ApplicationContext
import java.time.LocalDate
import java.time.LocalDateTime

class StockEntityListener(
    private val applicationContext: ApplicationContext
) {

    private lateinit var stockHistoryRepository: StockHistoryRepository

    private fun initializeRepository() {
        if (!::stockHistoryRepository.isInitialized) {
            stockHistoryRepository = applicationContext.getBean(StockHistoryRepository::class.java)
        }
    }

    @PostLoad
    fun onPostLoad(stock: Stock) {
        stock.previousStockCode = stock.stockCode
        stock.previousExchangeCode = stock.exchangeCode
    }

    @PostPersist
    fun onPersist(stock: Stock) {
        initializeRepository()
        val stockHistory = StockHistory(
            stockId = stock.id,
            afterExchangeCode = stock.exchangeCode,
            afterStockCode = stock.stockCode,
            beforeStockCode = stock.previousStockCode,
            beforeExchangeCode = stock.previousExchangeCode,
            changeDate = LocalDateTime.now().toLocalDate(),
        )

        stockHistoryRepository.save(stockHistory)
    }

    @PreUpdate
    fun onUpdate(stock: Stock) {
        initializeRepository()

        val isStockCodeChanged = stock.previousStockCode != stock.stockCode
        val isExchangeCodeChanged = stock.previousExchangeCode != stock.exchangeCode

        if (isStockCodeChanged || isExchangeCodeChanged) {
            stockHistoryRepository.save(
                StockHistory(
                    stockId = stock.id,
                    afterExchangeCode = stock.exchangeCode,
                    afterStockCode = stock.stockCode,
                    beforeStockCode = stock.previousStockCode,
                    beforeExchangeCode = stock.previousExchangeCode,
                    changeDate = LocalDate.now(),
                )
            )
        }
    }
}