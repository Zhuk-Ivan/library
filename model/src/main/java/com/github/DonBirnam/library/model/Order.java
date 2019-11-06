package com.github.DonBirnam.library.model;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long bookId;
    private Long userId;
    private String userLogin;
    private String userRole;
    private String bookTitle;
    private String bookAuthorFN;
    private String bookAuthorLN;
    private LocalDateTime takeDate;
    private LocalDateTime expireDate;

    public Order(Long id, Long bookId, Long userId, LocalDateTime takeDate, LocalDateTime expireDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.takeDate = takeDate;
        this.expireDate = expireDate;
    }

    public Order(Long userId, String userLogin, String userRole, String bookTitle, String bookAuthorFN, String bookAuthorLN, LocalDateTime takeDate, LocalDateTime expireDate) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userRole = userRole;
        this.bookTitle = bookTitle;
        this.bookAuthorFN = bookAuthorFN;
        this.bookAuthorLN = bookAuthorLN;
        this.takeDate = takeDate;
        this.expireDate = expireDate;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthorFN() {
        return bookAuthorFN;
    }

    public void setBookAuthorFN(String bookAuthorFN) {
        this.bookAuthorFN = bookAuthorFN;
    }

    public String getBookAuthorLN() {
        return bookAuthorLN;
    }

    public void setBookAuthorLN(String bookAuthorLN) {
        this.bookAuthorLN = bookAuthorLN;
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
