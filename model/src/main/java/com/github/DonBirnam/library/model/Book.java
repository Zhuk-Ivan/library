package com.github.DonBirnam.library.model;

public class Book {
    private Long id;
    private String title;
    private Long authorId;
    private int pageCount;
    private String isbn;
    private String genre;
    private String authorFirstName;
    private String authorLastName;

    public Book(Long id, String title, int pageCount, String isbn, String genre, String authorFirstName, String authorLastName) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
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

    public Book(Long id, String title, Long authorId, int pageCount, String isbn, String genre) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}

