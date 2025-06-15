package jhkim105.tutorials.stock.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "stock_day_price")
data class StockDayPrice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val exchangeCode: String,

    @Column(nullable = false)
    val stockCode: String,

    @Column(nullable = false)
    val businessDate: LocalDate,

    @Column(nullable = false)
    val price: Double
)