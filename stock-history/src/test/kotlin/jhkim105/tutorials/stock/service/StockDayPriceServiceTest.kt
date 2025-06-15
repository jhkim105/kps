package jhkim105.tutorials.stock.service

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeGreaterThanOrEqualTo
import io.kotest.matchers.shouldBe
import jhkim105.tutorials.stock.repository.StockDayPriceRepository
import jhkim105.tutorials.stock.repository.StockHistoryRepository
import jhkim105.tutorials.stock.repository.StockRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import java.time.LocalDate
import kotlin.test.Test

@SpringBootTest
class StockDayPriceServiceTest @Autowired constructor(
    val stockDayPriceService: StockDayPriceService,
    val stockDayPriceRepository: StockDayPriceRepository,
    val stockHistoryRepository: StockHistoryRepository,
    val stockRepository: StockRepository
) {

    val stockCode = "B"
    val exchangeCode = "202"

    @Test
    @Sql(scripts = ["/stockDayPrice.sql"])
    fun `종목 히스토리를 이용해서 가격 조회하기`() {
        // given
        val stock = stockRepository.findByStockCodeAndExchangeCode(stockCode, exchangeCode)!!
        val date = LocalDate.of(2025, 6, 10)

        // when
        val prices = stockDayPriceService.findPricesByStockAndDate(stock, date, 10)

        // then
        prices shouldHaveSize 7
        prices[0].businessDate shouldBeGreaterThanOrEqualTo prices[1].businessDate
        prices[1].businessDate shouldBeGreaterThanOrEqualTo prices[2].businessDate
    }
}
