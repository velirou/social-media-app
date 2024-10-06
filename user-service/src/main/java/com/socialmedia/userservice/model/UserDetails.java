package com.socialmedia.userservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "user_details", schema = "user-service")
public class UserDetails {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String bio;

    @Column
    private String location;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    // Default constructor
    public UserDetails() {}

    public UserDetails(Long userId, String fullName, String bio, String location, LocalDate dateOfBirth) {
        this.userId = userId;
        this.fullName = fullName;
        this.bio = bio;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
