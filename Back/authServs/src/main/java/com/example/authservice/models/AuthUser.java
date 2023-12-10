package com.example.authservice.models;

import jakarta.persistence.*;

@Entity
public class AuthUser {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String hashed_pass;

    @Column(nullable = false)
    private String userRole;

    public AuthUser(Long userId, String username, String hashed_pass, String role) {
        this.username = username;
        this.hashed_pass = hashed_pass;
        this.userRole = role;
    }

    public AuthUser() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashed_pass() {
        return hashed_pass;
    }

    public void setHashed_pass(String hashed_pass) {
        this.hashed_pass = hashed_pass;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String role) {
        this.userRole = role;
    }
}
