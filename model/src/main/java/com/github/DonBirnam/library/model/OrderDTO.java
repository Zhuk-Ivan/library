package com.github.DonBirnam.library.model;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderDTO {
    private Long id;
    private Set<Book> books;
    private UserDTO userDTO;
    private LocalDateTime createDate;
    private LocalDateTime takeDate;
    private LocalDateTime expireDate;

    public OrderDTO(Long id, Set<Book> books, UserDTO userDTO, LocalDateTime createDate, LocalDateTime takeDate, LocalDateTime expireDate) {
        this.id = id;
        this.books = books;
        this.userDTO = userDTO;
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
