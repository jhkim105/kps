package jhkim105.tutorials.kps.financial.adapter.web

import jhkim105.tutorials.kps.financial.application.port.FinancialReportDto
import jhkim105.tutorials.kps.financial.application.port.FinancialReportUseCase
import jhkim105.tutorials.kps.financial.domain.PeriodType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/financials")
class FinancialController(
    private val financialReportUseCase: FinancialReportUseCase
) {
    @GetMapping
    fun getFinancials(
        @RequestParam symbol: String,
        @RequestParam periodType: PeriodType,
        @RequestParam period: String
    ): ResponseEntity<List<FinancialReportDto>> {
        val result = financialReportUseCase.getFinancialReport(symbol, period, periodType)
        return ResponseEntity.ok(result)
    }
}