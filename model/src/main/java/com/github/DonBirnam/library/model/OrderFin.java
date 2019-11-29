package com.github.DonBirnam.library.model;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderFin {
    private Long id;
    private String login;
    private LocalDateTime createOrder;
    private LocalDateTime takeDate;
    private LocalDateTime expireDate;
    private Set<BookFull> books;

    public OrderFin(Long id, String login, LocalDateTime createOrder, LocalDateTime takeDate, LocalDateTime expireDate, Set<BookFull> books) {
        this.id = id;
        this.login = login;
        this.createOrder = createOrder;
        this.takeDate = takeDate;
        this.expireDate = expireDate;
        this.books = books;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<BookFull> getBooks() {
        return books;
    }

    public void setBooks(Set<BookFull> books) {
        this.books = books;
    }

    public LocalDateTime getCreateOrder() {
        return createOrder;
    }

    public void setCreateOrder(LocalDateTime createOrder) {
        this.createOrder = createOrder;
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
