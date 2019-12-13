package com.github.DonBirnam.library.dao.entity;

import com.github.DonBirnam.library.model.BookStatus;
import com.github.DonBirnam.library.model.Genre;
import com.github.DonBirnam.library.model.OrderStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class BookEntity {
    private Long id;
    private String title;
    private AuthorEntity authorEntity;
    private Set<OrderEntity> orders = new HashSet<>();
    private int pageCount;
    private String isbn;
    private Genre genre;
    private BookStatus status;
    private int inStock;
    private LocalDateTime nearestDateToReturn;


    public BookEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }

    @Column(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Column
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column
    @Enumerated(EnumType.STRING)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Column
    @Enumerated(EnumType.STRING)
    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Column(name = "in_stock")
    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_order", joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    @Transient
    public LocalDateTime getNearestDateToReturn() {
//        boolean isIssued = orders.stream().anyMatch(orderEntity -> orderEntity.getOrderStatus().equals(OrderStatus.ISSUED));
        boolean isIssued = orders.stream().map(OrderEntity::getOrderStatus).anyMatch(orderStatus -> orderStatus.equals(OrderStatus.ISSUED));
        if (!orders.isEmpty() && isIssued) {
            return orders.stream().min(Comparator.comparing(OrderEntity::getExpireDate)).get().getExpireDate();
        }
        else {
            return null;
        }
    }

    public void setNearestDateToReturn(LocalDateTime nearestDateToReturn) {
        this.nearestDateToReturn = nearestDateToReturn;
    }
}
//                        bookEntity.getOrders().stream().map(orderEntity -> orderEntity.getExpireDate()).min(LocalDateTime::compareTo).get());
//                        bookEntity.getOrders().stream().map(orderEntity -> orderEntity.getExpireDate()).min(LocalDateTime::compareTo).get());
//                        bookEntity.getOrders().stream().min(Comparator.comparing(OrderEntity::getExpireDate)).get().getExpireDate());