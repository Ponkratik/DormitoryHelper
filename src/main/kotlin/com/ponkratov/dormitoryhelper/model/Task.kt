package com.ponkratov.dormitoryhelper.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "`task`", schema = "iis")
class Task(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "name") val name: String = "",
    @Basic @Column(name = "description") val description: String = "",
    @Basic @Column(name = "publication_date") val publicationDate: Date = Date(0),
    @Basic @Column(name = "end_date") val endDate: Date = Date(0),
    @ManyToOne @JoinColumn(
        name = "published_id",
        referencedColumnName = "id",
        nullable = false
    ) val userByPublishedId: User? = null,
    @ManyToOne @JoinColumn(name = "user_picked", referencedColumnName = "id") val userByUserPicked: User? = null
)