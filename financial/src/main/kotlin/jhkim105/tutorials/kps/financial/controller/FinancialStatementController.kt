package jhkim105.tutorials.kps.financial.controller

import jhkim105.tutorials.kps.financial.entity.PeriodType
import jhkim105.tutorials.kps.financial.service.FinancialStatementDto
import jhkim105.tutorials.kps.financial.service.FinancialStatementService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/financials")
class FinancialStatementController(
    private val financialStatementService: FinancialStatementService
) {
    @GetMapping
    fun getFinancials(
        @RequestParam symbol: String,
        @RequestParam periodType: PeriodType,
        @RequestParam period: String
    ): ResponseEntity<FinancialStatementDto> {
        val response = financialStatementService.getFinancialStatement(symbol, periodType, period)
        return ResponseEntity.ok(response)
    }
}