package com.ponkratov.dormitoryhelper.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*


@Entity
@Table(name = "`user`", schema = "iis")
class User(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "`id`") var id: Long = 0,
    @Basic @Column(name = "`fio`") var fio: String = "",
    @Basic @Column(name = "`email`") var email: String = "",
    @Basic @Column(name = "`password`") @JsonIgnore var password: String = "",
    @Basic @Column(name = "`login`") var login: String = "",
    @Basic @Column(name = "`dormitory`") var dormitory: Long = 0,
    @Basic @Column(name = "`room`") var room: String = "",
    @Basic @Column(name = "`group`") var group: String = "",
    @ManyToOne @JoinColumn(
        name = "role_id", referencedColumnName = "id", nullable = false, insertable = true, updatable = true
    ) var roleByRoleId: Role = Role(5, RoleEnum.ROLE_STUDENT.name)
)