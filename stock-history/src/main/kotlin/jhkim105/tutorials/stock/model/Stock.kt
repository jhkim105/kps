package jhkim105.tutorials.stock.model

import jakarta.persistence.*
import org.springframework.context.ApplicationContext
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "stock")
class Stock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var exchangeCode: String,
    var stockCode: String,

    @CreatedDate
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null
) {
    @Transient
    var previousStockCode: String? = null

    @Transient
    var previousExchangeCode: String? = null
}

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
            exchangeCode = stock.exchangeCode,
            stockCode = stock.stockCode,
            changeDate = LocalDateTime.now().toLocalDate(),
            createdAt = LocalDateTime.now()
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
                    exchangeCode = stock.exchangeCode,
                    stockCode = stock.stockCode,
                    changeDate = LocalDate.now(),
                    createdAt = LocalDateTime.now()
                )
            )
        }
    }
}

interface StockRepository : JpaRepository<Stock, Long>