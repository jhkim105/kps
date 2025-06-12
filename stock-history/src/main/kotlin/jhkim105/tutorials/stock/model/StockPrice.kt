package jhkim105.tutorials.stock.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "stock_price")
class StockPrice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val exchangeCode: String,
    val stockCode: String,
    val businessDate: LocalDate,
    val price: BigDecimal
)

interface StockPriceRepository : JpaRepository<StockPrice, Long> {
    fun findTopByExchangeCodeAndStockCodeAndBusinessDateLessThanEqualOrderByBusinessDateDesc(
        exchangeCode: String,
        stockCode: String,
        businessDate: LocalDate
    ): StockPrice?

    fun findByExchangeCodeAndStockCodeAndBusinessDateLessThanEqualOrderByBusinessDateDesc(
        exchangeCode: String,
        stockCode: String,
        businessDate: LocalDate
    ): List<StockPrice>
}