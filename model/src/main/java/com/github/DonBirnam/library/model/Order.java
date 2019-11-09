package com.github.DonBirnam.library.model;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDateTime createDate;
    private LocalDateTime takeDate;
    private LocalDateTime expireDate;

    public Order(Long id, Long bookId, Long userId, LocalDateTime createDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.createDate = createDate;
    }

    public Order(Long id, Long bookId, Long userId, LocalDateTime createDate, LocalDateTime takeDate, LocalDateTime expireDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.createDate = createDate;
        this.takeDate = takeDate;
        this.expireDate = expireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

}
