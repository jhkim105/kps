package jhkim105.tutorials.stock.model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jhkim105.tutorials.stock.repository.StockDayPriceRepository
import jhkim105.tutorials.stock.repository.StockHistoryRepository
import jhkim105.tutorials.stock.repository.StockRepository
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class StockEntityListenerTest(
    private val stockRepository: StockRepository,
    private val stockHistoryRepository: StockHistoryRepository,
) : StringSpec({

    beforeTest {
        stockHistoryRepository.deleteAll()
        stockRepository.deleteAll()
    }

    afterTest {
        stockHistoryRepository.deleteAll()
        stockRepository.deleteAll()
    }

    "should create StockHistory on initial persist" {
        // when
        val stock = Stock(exchangeCode = "KOSPI", stockCode = "A005930")
        stockRepository.save(stock)

        // then
        val histories = stockHistoryRepository.findAll()
        histories shouldHaveSize 1
        histories.first().beforeStockCode shouldBe null
        histories.first().afterStockCode shouldBe "A005930"
        histories.first().beforeExchangeCode shouldBe null
        histories.first().afterExchangeCode shouldBe "KOSPI"
    }

    "should create StockHistory when stock is updated" {
        // given
        val stock = stockRepository.save(Stock(exchangeCode = "KOSPI", stockCode = "A005930"))

        // flush & clear to trigger PostLoad
        stockRepository.flush()

        // when: exchangeCode 변경
        stock.exchangeCode = "KOSDAQ"
        stockRepository.save(stock)

        // then
        val histories = stockHistoryRepository.findAll()
        histories shouldHaveSize 2
        histories[1].beforeStockCode shouldBe "A005930"
        histories[1].afterStockCode shouldBe "A005930"
        histories[1].beforeExchangeCode shouldBe "KOSPI"
        histories[1].afterExchangeCode shouldBe "KOSDAQ"
    }

    "should not create StockHistory when no change occurs" {
        // given
        val stock = stockRepository.save(Stock(exchangeCode = "KOSPI", stockCode = "A005930"))

        // flush & clear to trigger PostLoad
        stockRepository.flush()

        // when: 같은 값으로 다시 저장
        stock.stockCode = "A005930"
        stock.exchangeCode = "KOSPI"
        stockRepository.save(stock)

        // then
        val histories = stockHistoryRepository.findAll()
        histories shouldHaveSize 1 // only initial insert
    }
})
