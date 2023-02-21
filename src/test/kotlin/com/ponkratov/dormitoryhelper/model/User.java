package com.ponkratov.dormitoryhelper.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "role_id")
    private long roleId;
    @Basic
    @Column(name = "dormitory")
    private long dormitory;
    @Basic
    @Column(name = "room")
    private String room;
    @Basic
    @Column(name = "group")
    private String group;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Duty> dutiesById;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Invoice> invoicesById;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role roleByRoleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getDormitory() {
        return dormitory;
    }

    public void setDormitory(long dormitory) {
        this.dormitory = dormitory;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && dormitory == user.dormitory && Objects.equals(login, user.login) && Objects.equals(email, user.email) && Objects.equals(room, user.room) && Objects.equals(group, user.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, roleId, dormitory, room, group);
    }

    public Collection<Duty> getDutiesById() {
        return dutiesById;
    }

    public void setDutiesById(Collection<Duty> dutiesById) {
        this.dutiesById = dutiesById;
    }

    public Collection<Invoice> getInvoicesById() {
        return invoicesById;
    }

    public void setInvoicesById(Collection<Invoice> invoicesById) {
        this.invoicesById = invoicesById;
    }

    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
