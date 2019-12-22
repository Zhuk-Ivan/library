package com.github.DonBirnam.library.model.User;

import com.github.DonBirnam.library.model.BookFull;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class BlockedUser {
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Role role;
    private Map<LocalDateTime, Set<BookFull>> booksToReturn;

    public BlockedUser(Long id, String login, String firstName, String lastName, String phone, String email, Role role, Map<LocalDateTime, Set<BookFull>> booksToReturn) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.booksToReturn = booksToReturn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Map<LocalDateTime, Set<BookFull>> getBooksToReturn() {
        return booksToReturn;
    }

    public void setBooksToReturn(Map<LocalDateTime, Set<BookFull>> booksToReturn) {
        this.booksToReturn = booksToReturn;
    }
}
