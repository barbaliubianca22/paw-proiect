package com.example.authservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TokenSession {
    @Id
    private String token;

    @Column(nullable = false)
    private Long expirationDate;

    public TokenSession(String token, Long expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public TokenSession() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }
}
