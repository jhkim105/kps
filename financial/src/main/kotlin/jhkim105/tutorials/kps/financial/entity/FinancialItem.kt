package jhkim105.tutorials.kps.financial.entity

import jakarta.persistence.*

@Entity
@Table(name = "financial_item")
class FinancialItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    val seq: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: FinancialItem? = null,

    @OneToMany(mappedBy = "parent")
    val children: List<FinancialItem> = mutableListOf(),

    val depth: Int
)