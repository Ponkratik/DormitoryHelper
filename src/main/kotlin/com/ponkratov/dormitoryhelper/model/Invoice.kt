package com.ponkratov.dormitoryhelper.model

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "`invoice`", schema = "iis")
class Invoice(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "month") val month: Int = 0,
    @Basic @Column(name = "year") val year: Int = 0,
    @Basic @Column(name = "date_invoiced") val dateInvoiced: Date = Date(0),
    @Basic @Column(name = "sum") val sum: BigDecimal? = null,
    @Basic @Column(name = "payment_date") val paymentDate: Date? = null,
    @ManyToOne @JoinColumn(
        name = "user_id",
        referencedColumnName = "id",
        nullable = false
    ) val userByUserId: User? = null
)