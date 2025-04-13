package jhkim105.tutorials.kps.financial.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "industry")
class Industry(
    @Id
    val id: String,
    val name: String
)