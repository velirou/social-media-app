package com.socialmedia.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "user_settings", schema = "user-service")
public class UserSettings {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Column(name = "receive_notifications")
    private Boolean receiveNotifications = Boolean.TRUE;

    @Column(name = "dark_mode")
    private Boolean darkMode;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    // Default constructor
    public UserSettings() {}

    public UserSettings(Long userId, Boolean isPrivate, Boolean receiveNotifications, Boolean darkMode) {
        this.userId = userId;
        this.isPrivate = isPrivate;
        this.receiveNotifications = receiveNotifications;
        this.darkMode = darkMode;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Boolean getReceiveNotifications() {
        return receiveNotifications;
    }

    public void setReceiveNotifications(Boolean receiveNotifications) {
        this.receiveNotifications = receiveNotifications;
    }

    public Boolean getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(Boolean darkMode) {
        this.darkMode = darkMode;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
