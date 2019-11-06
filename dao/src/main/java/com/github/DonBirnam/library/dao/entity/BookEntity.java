package com.github.DonBirnam.library.dao.entity;

import com.github.DonBirnam.library.model.Author;
import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @ManyToOne
    @JoinColumn(name = "id")
    private Author author;
    @Column(name = "page_count")
    private int pageCount;
    @Column
    private String isbn;
    @Column
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public BookEntity() {
    }

    public BookEntity(Long id, String title, Author author, int pageCount, String isbn, Genre genre, BookStatus status) {
        this.id = id;
        this.title = title;
        this.author = author;
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

    public void setAuthor(Author author) {
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
