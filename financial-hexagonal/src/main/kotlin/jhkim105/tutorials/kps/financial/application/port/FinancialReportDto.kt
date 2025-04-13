package jhkim105.tutorials.kps.financial.application.port

import java.math.BigDecimal

data class FinancialReportDto(
    val itemId: Long,
    val parentItemId: Long?,
    val itemName: String,
    val value: BigDecimal,
    val depth: Int
)