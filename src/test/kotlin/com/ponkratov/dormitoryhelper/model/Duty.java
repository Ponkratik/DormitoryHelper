package com.ponkratov.dormitoryhelper.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Duty {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "dormitory")
    private long dormitory;
    @Basic
    @Column(name = "floor")
    private long floor;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "start_date")
    private Date startDate;
    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @Basic
    @Column(name = "finished")
    private boolean finished;
    @Basic
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userByUserId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDormitory() {
        return dormitory;
    }

    public void setDormitory(long dormitory) {
        this.dormitory = dormitory;
    }

    public long getFloor() {
        return floor;
    }

    public void setFloor(long floor) {
        this.floor = floor;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duty duty = (Duty) o;
        return id == duty.id && dormitory == duty.dormitory && floor == duty.floor && userId == duty.userId && finished == duty.finished && Objects.equals(startDate, duty.startDate) && Objects.equals(endDate, duty.endDate) && Objects.equals(comment, duty.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dormitory, floor, userId, startDate, endDate, finished, comment);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
