package com.github.DonBirnam.library.dao.entity;

import com.github.DonBirnam.library.model.Role;

import javax.persistence.*;

@Entity
@Table(name = "auth_users")
public class AuthUserEntity {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private UserEntity userEntity;

    public AuthUserEntity() {
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

    @Column
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    @Column
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
