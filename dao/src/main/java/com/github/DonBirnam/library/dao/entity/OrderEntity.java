package com.github.DonBirnam.library.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity {
    private Long id;
    private Set<BookEntity> books = new HashSet<>();
    private AuthUserEntity authUserEntity;
    private LocalDateTime createDate;
    private LocalDateTime takeDate;
    private LocalDateTime expireDate;

    public OrderEntity() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public AuthUserEntity getAuthUserEntity() {
        return authUserEntity;
    }

    public void setAuthUserEntity(AuthUserEntity authUserEntity) {
        this.authUserEntity = authUserEntity;
    }

    @Column(name = "create_date")
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Column(name = "take_date")
    public LocalDateTime getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(LocalDateTime takeDate) {
        this.takeDate = takeDate;
    }

    @Column(name = "expire_date")
    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }
}
