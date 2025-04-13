package jhkim105.tutorials.kps.financial.service

import java.math.BigDecimal

data class FinancialStatementDto(
    val symbol: String,
    val industry: String,
    val items: List<FinancialItemDto>
)

data class FinancialItemDto(
    val itemId: Long,
    val parentItemId: Long?,
    val itemName: String,
    val value: BigDecimal,
    val depth: Int
)