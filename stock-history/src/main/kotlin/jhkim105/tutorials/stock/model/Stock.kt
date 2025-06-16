package jhkim105.tutorials.stock.model

import jakarta.persistence.*
import jhkim105.tutorials.stock.StockEntityListener
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "stock")
//@EntityListeners(StockEntityListener::class)
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