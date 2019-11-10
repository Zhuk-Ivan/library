package com.github.DonBirnam.library.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Order {
    private Long id;
    private Set<Book> books = new HashSet<>();
    private Book book;
    private AuthUser authUser;
    private LocalDateTime createDate;
    private LocalDateTime takeDate;
    private LocalDateTime expireDate;

    public Order(Long id, Set<Book> books, AuthUser authUser, LocalDateTime createDate, LocalDateTime takeDate, LocalDateTime expireDate) {
        this.id = id;
        this.books = books;
        this.authUser = authUser;
        this.createDate = createDate;
        this.takeDate = takeDate;
        this.expireDate = expireDate;
    }

    public Order(Long id, Book book, AuthUser authUser, LocalDateTime createDate, LocalDateTime takeDate, LocalDateTime expireDate) {
        this.id = id;
        this.book = book;
        this.authUser = authUser;
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
