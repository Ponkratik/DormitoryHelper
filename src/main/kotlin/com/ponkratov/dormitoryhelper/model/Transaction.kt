package com.ponkratov.dormitoryhelper.model

import javax.persistence.*

@Entity
@Table(name = "`transaction`", schema = "iis")
class Transaction(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "reward") val reward: Int = 0,
    @Basic @Column(name = "comment") val comment: String = "",
    @ManyToOne @JoinColumn(
        name = "user_from",
        referencedColumnName = "id",
        nullable = false
    ) val userByUserFrom: User? = null,
    @ManyToOne @JoinColumn(
        name = "user_to",
        referencedColumnName = "id",
        nullable = false
    ) val userByUserTo: User? = null
)