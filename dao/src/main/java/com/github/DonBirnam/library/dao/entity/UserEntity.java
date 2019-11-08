package com.github.DonBirnam.library.dao.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private AuthUserEntity authUserEntity;
    private List<OrderEntity> orders = new ArrayList<>();

    public UserEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(mappedBy = "userEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public AuthUserEntity getAuthUserEntity() {
        return authUserEntity;
    }

    public void setAuthUserEntity(AuthUserEntity authUserEntity) {
        this.authUserEntity = authUserEntity;
    }

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
