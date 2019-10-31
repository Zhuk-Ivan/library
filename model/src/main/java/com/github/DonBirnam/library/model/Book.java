package com.github.DonBirnam.library.model;

public class Book {
    private Long id;
    private String title;
    private Long authorId;
    private int pageCount;
    private String isbn;
    private String genre;
//    private User user;
//    private LocalDateTime takeDate;
//    private LocalDateTime expiredDate;

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


//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public LocalDateTime getTakeDate() {
//        return takeDate;
//    }
//
//    public void setTakeDate(LocalDateTime takeDate) {
//        this.takeDate = takeDate;
//    }
//
//    public LocalDateTime getExpiredDate() {
//        return expiredDate;
//    }
//
//    public void setExpiredDate(LocalDateTime expiredDate) {
//        this.expiredDate = expiredDate;
//    }
}
