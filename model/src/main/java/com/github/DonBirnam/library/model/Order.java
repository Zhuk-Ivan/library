package com.github.DonBirnam.library.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Order {
    private Long id;
    private Set<Long> booksId = new HashSet<>();
    private Long bookId;
    private Long authUserId;
    private LocalDateTime createDate;
    private LocalDateTime takeDate;
    private LocalDateTime expireDate;
    private OrderStatus orderStatus;


    public Order(Long id, Set<Long> booksId, Long authUserId, LocalDateTime createDate, LocalDateTime takeDate, LocalDateTime expireDate, OrderStatus orderStatus) {
        this.id = id;
        this.booksId = booksId;
        this.authUserId = authUserId;
        this.createDate = createDate;
        this.takeDate = takeDate;
        this.expireDate = expireDate;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getBooks() {
        return booksId;
    }

    public void setBooks(Set<Long> books) {
        this.booksId = books;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Long authUserId) {
        this.authUserId = authUserId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(LocalDateTime takeDate) {
        this.takeDate = takeDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public Set<Long> getBooksId() {
        return booksId;
    }

    public void setBooksId(Set<Long> booksId) {
        this.booksId = booksId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
