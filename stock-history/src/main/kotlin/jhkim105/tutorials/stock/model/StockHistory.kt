package jhkim105.tutorials.stock.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "stock_history")
class StockHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val stockId: Long,
    val exchangeCode: String, // 이전 코드
    val stockCode: String, // 이전 코드
    val changeDate: LocalDate, // 변경 일자
    val createdAt: LocalDateTime = LocalDateTime.now()
)

interface StockHistoryRepository : JpaRepository<StockHistory, Long> {

    fun findTopByExchangeCodeAndStockCodeAndChangeDateLessThanEqualOrderByChangeDateDesc(
        exchangeCode: String,
        stockCode: String,
        businessDate: LocalDate
    ): StockHistory?
}