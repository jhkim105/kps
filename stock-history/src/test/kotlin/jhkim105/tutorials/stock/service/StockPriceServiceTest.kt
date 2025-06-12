package jhkim105.tutorials.stock.service

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.test.Test

@SpringBootTest
class StockPriceServiceTest {

    @Autowired
    lateinit var stockPriceService: StockPriceService

    @Test
    fun `should return price from initial code before change`() {
        val result = stockPriceService.findPriceByStockIdAndDate(1L, LocalDate.of(2025, 1, 6))
        result?.price shouldBe BigDecimal("93.00")
    }

    @Test
    fun `should return price from second code after change`() {
        val result = stockPriceService.findPriceByStockIdAndDate(1L, LocalDate.of(2025, 1, 12))
        result?.price shouldBe BigDecimal("104.00")
    }
}
