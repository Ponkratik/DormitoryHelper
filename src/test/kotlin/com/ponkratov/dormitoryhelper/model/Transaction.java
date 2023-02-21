package com.ponkratov.dormitoryhelper.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_from")
    private long userFrom;
    @Basic
    @Column(name = "user_to")
    private long userTo;
    @Basic
    @Column(name = "reward")
    private int reward;
    @Basic
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_from", referencedColumnName = "id", nullable = false)
    private User userByUserFrom;
    @ManyToOne
    @JoinColumn(name = "user_to", referencedColumnName = "id", nullable = false)
    private User userByUserTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(long userFrom) {
        this.userFrom = userFrom;
    }

    public long getUserTo() {
        return userTo;
    }

    public void setUserTo(long userTo) {
        this.userTo = userTo;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
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
        Transaction that = (Transaction) o;
        return id == that.id && userFrom == that.userFrom && userTo == that.userTo && reward == that.reward && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userFrom, userTo, reward, comment);
    }

    public User getUserByUserFrom() {
        return userByUserFrom;
    }

    public void setUserByUserFrom(User userByUserFrom) {
        this.userByUserFrom = userByUserFrom;
    }

    public User getUserByUserTo() {
        return userByUserTo;
    }

    public void setUserByUserTo(User userByUserTo) {
        this.userByUserTo = userByUserTo;
    }
}
