package com.ponkratov.dormitoryhelper.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Task {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "published_id")
    private long publishedId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "publication_date")
    private Date publicationDate;
    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @Basic
    @Column(name = "user_picked")
    private long userPicked;
    @ManyToOne
    @JoinColumn(name = "published_id", referencedColumnName = "id", nullable = false)
    private User userByPublishedId;
    @ManyToOne
    @JoinColumn(name = "user_picked", referencedColumnName = "id")
    private User userByUserPicked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPublishedId() {
        return publishedId;
    }

    public void setPublishedId(long publishedId) {
        this.publishedId = publishedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getUserPicked() {
        return userPicked;
    }

    public void setUserPicked(long userPicked) {
        this.userPicked = userPicked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && publishedId == task.publishedId && userPicked == task.userPicked && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(publicationDate, task.publicationDate) && Objects.equals(endDate, task.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publishedId, name, description, publicationDate, endDate, userPicked);
    }

    public User getUserByPublishedId() {
        return userByPublishedId;
    }

    public void setUserByPublishedId(User userByPublishedId) {
        this.userByPublishedId = userByPublishedId;
    }

    public User getUserByUserPicked() {
        return userByUserPicked;
    }

    public void setUserByUserPicked(User userByUserPicked) {
        this.userByUserPicked = userByUserPicked;
    }
}
