package com.ponkratov.dormitoryhelper.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "`duty`", schema = "iis")
class Duty(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "dormitory") val dormitory: Long = 0,
    @Basic @Column(name = "floor") val floor: Long = 0,
    @Basic @Column(name = "start_date") val startDate: Date = Date(0),
    @Basic @Column(name = "end_date") val endDate: Date = Date(0),
    @Basic @Column(name = "finished") val finished: Boolean = false,
    @Basic @Column(name = "comment") val comment: String = "",
    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id") val userByUserId: User? = null
)