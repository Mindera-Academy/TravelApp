package com.User.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column(unique = true)
    private String vatNumber;

    @Embedded
    private Contact contacts;

    @Transient
    private String confirmPassword;

    public User() {
    }

    public User(Integer id, String name, String username, String password, String vatNumber, Contact contacts, String confirmPassword) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.vatNumber = vatNumber;
        this.contacts = contacts;
        this.confirmPassword = confirmPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public Contact getContacts() {
        return contacts;
    }

    public void setContacts(Contact contacts) {
        this.contacts = contacts;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}