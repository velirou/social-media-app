package com.socialmedia.userservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "users", schema = "user-service")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;

    public User() {
    }

    public User(Long id, String username, String email, String password_hash) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = password_hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
}
