package jhkim105.tutorials.kps.financial.adapter.persistence.entity

import jakarta.persistence.*
import jhkim105.tutorials.kps.financial.domain.Industry

@Entity
@Table(name = "stock")
class StockEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val symbol: String,             // 종목 코드 (예: A005930)

    val name: String,               // 종목 이름 (예: 삼성전자)

    @Enumerated(EnumType.STRING)
    val industry: Industry         // 업종 코드 연동
)