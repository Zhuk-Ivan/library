package com.github.DonBirnam.library.dao.entity;

import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;

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

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
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
