package com.github.DonBirnam.library.model;

import java.util.Date;

public class Book {
    private Long id;
    private String title;
    private Author author;
    private int page_count;
    private String isbn;
    private Genre genre;
    private User user;
    private Date takeDate;
    private Date expiredDate;

    public Book() {
    }

    public Book(Long id, String title, Author author, int page_count, String isbn, Genre genre, User user, Date takeDate, Date expiredDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.page_count = page_count;
        this.isbn = isbn;
        this.genre = genre;
        this.user = user;
        this.takeDate = takeDate;
        this.expiredDate = expiredDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}
