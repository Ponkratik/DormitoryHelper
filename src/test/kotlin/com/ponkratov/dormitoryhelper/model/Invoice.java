package com.ponkratov.dormitoryhelper.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Invoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "month")
    private int month;
    @Basic
    @Column(name = "year")
    private int year;
    @Basic
    @Column(name = "date_invoiced")
    private Date dateInvoiced;
    @Basic
    @Column(name = "sum")
    private BigDecimal sum;
    @Basic
    @Column(name = "payment_date")
    private Date paymentDate;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getDateInvoiced() {
        return dateInvoiced;
    }

    public void setDateInvoiced(Date dateInvoiced) {
        this.dateInvoiced = dateInvoiced;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && userId == invoice.userId && month == invoice.month && year == invoice.year && Objects.equals(dateInvoiced, invoice.dateInvoiced) && Objects.equals(sum, invoice.sum) && Objects.equals(paymentDate, invoice.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, month, year, dateInvoiced, sum, paymentDate);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
