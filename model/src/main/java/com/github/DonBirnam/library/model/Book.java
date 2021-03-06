package com.github.DonBirnam.library.model;

public class Book {
    private Long id;
    private String title;
    private int pageCount;
    private String isbn;
    private Genre genre;
    private BookStatus status;
    private int inStock;
    private Long authorId;

    public Book() {
    }

    public Book(Long id, String title, int pageCount, String isbn, Genre genre, BookStatus status, int inStock, Long authorId) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.status = status;
        this.inStock = inStock;
        this.authorId = authorId;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}

