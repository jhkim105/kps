package jhkim105.tutorials.stock.service

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jhkim105.tutorials.stock.model.Stock
import jhkim105.tutorials.stock.model.StockDayPrice
import jhkim105.tutorials.stock.model.StockHistory
import jhkim105.tutorials.stock.repository.StockDayPriceRepository
import jhkim105.tutorials.stock.repository.StockHistoryRepository
import jhkim105.tutorials.stock.repository.StockRepository
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest
@Transactional
class StockQueryServiceTest(
    private val stockQueryService: StockQueryService,
    private val stockRepository: StockRepository,
    private val stockHistoryRepository: StockHistoryRepository,
) : StringSpec({

    beforeTest {
        val stock = stockRepository.save(Stock(exchangeCode = "202", stockCode = "B"))

        stockHistoryRepository.saveAll(
            listOf(
                StockHistory(
                    id = 1,
                    stockId = stock.id,
                    changeDate = LocalDate.of(2025, 2, 1),
                    beforeExchangeCode = null,
                    beforeStockCode = null,
                    afterExchangeCode = "201",
                    afterStockCode = "A"
                ),
                StockHistory(
                    id = 2,
                    stockId = stock.id,
                    changeDate = LocalDate.of(2025, 5, 1),
                    beforeExchangeCode = "201",
                    beforeStockCode = "A",
                    afterExchangeCode = "201",
                    afterStockCode = "B"
                ),
                StockHistory(
                    id = 3,
                    stockId = stock.id,
                    changeDate = LocalDate.of(2025, 6, 1),
                    beforeExchangeCode = "201",
                    beforeStockCode = "B",
                    afterExchangeCode = "202",
                    afterStockCode = "B"
                )
            )
        )
    }

    afterTest {
        stockHistoryRepository.deleteAll()
        stockRepository.deleteAll()
    }


    "should return stock when using latest code (202, B) after June 1st 2025" {
        val result = stockQueryService.findStockBy(
            exchangeCode = "202",
            stockCode = "B",
            date = LocalDate.of(2025, 6, 2)
        )

        result.shouldNotBeNull()
    }

    "should return stock when using mid-level code (201, B) between May and June 2025" {
        val result = stockQueryService.findStockBy(
            exchangeCode = "201",
            stockCode = "B",
            date = LocalDate.of(2025, 5, 10)
        )

        result.shouldNotBeNull()
    }

    "should return stock when using original code (201, A) between Feb and May 2025" {
        val result = stockQueryService.findStockBy(
            exchangeCode = "201",
            stockCode = "A",
            date = LocalDate.of(2025, 3, 1)
        )

        result.shouldNotBeNull()
    }

    "should return null for unknown code on valid date" {
        val result = stockQueryService.findStockBy(
            exchangeCode = "999",
            stockCode = "XXX",
            date = LocalDate.of(2025, 6, 1)
        )

        result.shouldBeNull()
    }

    "should return null for valid code before any change exists" {
        val result = stockQueryService.findStockBy(
            exchangeCode = "201",
            stockCode = "A",
            date = LocalDate.of(2025, 1, 1)
        )

        result.shouldBeNull()
    }
})