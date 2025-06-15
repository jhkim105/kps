package jhkim105.tutorials.stock.service

import jhkim105.tutorials.stock.model.Stock
import jhkim105.tutorials.stock.model.StockDayPrice
import jhkim105.tutorials.stock.repository.StockDayPriceRepository
import jhkim105.tutorials.stock.repository.StockHistoryRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class StockDayPriceService(
    private val stockHistoryRepository: StockHistoryRepository,
    private val stockDayPriceRepository: StockDayPriceRepository
) {

//    fun findPricesByStockAndDate(stock: Stock, date: LocalDate, count: Int): List<StockDayPrice> {
//        // 지정일 기준으로 가장 최신 코드 이력 찾기
//        val history = stockHistoryRepository
//            .findTopByStockIdAndChangeDateLessThanEqualOrderByChangeDateDesc(stock.id, date)
//            ?: return emptyList()
//
//        return stockDayPriceRepository
//            .findByExchangeCodeAndStockCodeAndBusinessDateLessThanEqualOrderByBusinessDateDesc(
//                history.afterExchangeCode,
//                history.afterStockCode,
//                date
//            )
//            .take(count)
//    }

    fun findPricesByStockAndDate(stock: Stock, date: LocalDate, count: Int): List<StockDayPrice> {
        // 1. 해당 stock의 지정 날짜 이전 히스토리 전체 조회
        val histories = stockHistoryRepository.findByStockIdAndChangeDateLessThanEqual(stock.id, date)

        // 2. 해당 날짜 이전까지 유효한 코드 조합을 추출
        val validPairs = histories.map {
            it.afterExchangeCode to it.afterStockCode
        }.toSet()

        if (validPairs.isEmpty()) return emptyList()

        // 3. 해당 코드 조합들로 가격 데이터 조회
        val allPrices = validPairs.flatMap { (exchangeCode, stockCode) ->
            stockDayPriceRepository.findByExchangeCodeAndStockCodeAndBusinessDateLessThanEqualOrderByBusinessDateDesc(
                exchangeCode,
                stockCode,
                date
            )
        }

        // 4. 날짜 내림차순 정렬 후 count만큼 잘라내기
        return allPrices
            .sortedByDescending { it.businessDate }
            .take(count)
    }
}
