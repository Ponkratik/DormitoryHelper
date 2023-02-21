package com.ponkratov.dormitoryhelper.model

import javax.persistence.*

@Entity
@Table(name = "role", schema = "iis")
class Role(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "name") var name: String? = null
)
