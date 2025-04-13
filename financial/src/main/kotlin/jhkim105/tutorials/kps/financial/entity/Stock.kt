package jhkim105.tutorials.kps.financial.entity

import jakarta.persistence.*

@Entity
@Table(name = "stock")
class Stock(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val symbol: String,             // 종목 코드 (예: A005930)

    val name: String,               // 종목 이름 (예: 삼성전자)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industry_id")
    val industry: Industry          // 업종 코드 연동
)