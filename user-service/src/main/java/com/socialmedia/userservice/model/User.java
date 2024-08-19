package com.socialmedia.userservice.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "users", schema = "user-service")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password_hash;
    private Timestamp created_at;
    private Timestamp updated_at;

    public User() {
    }

    public User(Long id, String username, String email, String password_hash, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
