package com.github.DonBirnam.library.model;

public class Book {
    private Long id;
    private String title;
    private Author author;
    private int pageCount;
    private String isbn;
    private Genre genre;
    private BookStatus status;

    public Book(Long id, String title,Author author, int pageCount, String isbn, Genre genre,  BookStatus status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.status = status;
    }

    public Book(Long id, String title,int pageCount, String isbn, Genre genre, BookStatus status) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.status = status;
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

    public void setAuthor(Long author_id) {
        this.author = author;
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
}

