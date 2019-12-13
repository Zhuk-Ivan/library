package com.github.DonBirnam.library.model;

import java.time.LocalDateTime;

public class BookFull {
    private Long id;
    private String title;
    private int pageCount;
    private String isbn;
    private Genre genre;
    private BookStatus status;
    private int inStock;
    private String authorFirstName;
    private String authorLastName;
    private LocalDateTime nearestDateToReturn;


    public BookFull(Long id, String title, int pageCount, String isbn, Genre genre, BookStatus status, int inStock, String authorFirstName, String authorLastName, LocalDateTime nearestDateToReturn) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.status = status;
        this.inStock = inStock;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.nearestDateToReturn = nearestDateToReturn;
    }

    public BookFull(Long id, String title, int pageCount, String isbn, Genre genre, BookStatus status, int inStock, String authorFirstName, String authorLastName) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.status = status;
        this.inStock = inStock;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
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

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
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

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getFullAuthorName(){
        return authorFirstName + " " + authorLastName;
    }

    public LocalDateTime getNearestDateToReturn() {
        return nearestDateToReturn;
    }

    public void setNearestDateToReturn(LocalDateTime nearestDateToReturn) {
        this.nearestDateToReturn = nearestDateToReturn;
    }



}
