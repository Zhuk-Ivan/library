package com.github.DonBirnam.library.model;

import java.time.LocalDateTime;

public class OrderFin {
    private Long id;
    private String login;
    private String title;
    private String authorFN;
    private String authorLN;
    private LocalDateTime createOrder;
    private LocalDateTime takeDate;
    private LocalDateTime expireDate;

    public OrderFin(Long id, String login, String title, String authorFN, String authorLN, LocalDateTime createOrder, LocalDateTime takeDate, LocalDateTime expireDate) {
        this.id = id;
        this.login = login;
        this.title = title;
        this.authorFN = authorFN;
        this.authorLN = authorLN;
        this.createOrder = createOrder;
        this.takeDate = takeDate;
        this.expireDate = expireDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFN() {
        return authorFN;
    }

    public void setAuthorFN(String authorFN) {
        this.authorFN = authorFN;
    }

    public String getAuthorLN() {
        return authorLN;
    }

    public void setAuthorLN(String authorLN) {
        this.authorLN = authorLN;
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
