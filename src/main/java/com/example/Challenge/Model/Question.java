package com.example.Challenge.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;                  // e.g., "Unit 1: Computer System"
    @Column(columnDefinition = "TEXT")
    private String description;           // Short info about what's inside

    private String driveEmbedLink;        // Link to Google Drive embedded PDF
    private String grade = "12";          // Default grade
    private String subject = "Computer";  // Optional if you want multi-subject support

    private LocalDateTime uploadedAt;

    // Optional: createdBy if you want to track admins


    public Question(Long id, String title, String description, String driveEmbedLink, String grade, String subject, LocalDateTime uploadedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.driveEmbedLink = driveEmbedLink;
        this.grade = grade;
        this.subject = subject;
        this.uploadedAt = uploadedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDriveEmbedLink() {
        return driveEmbedLink;
    }

    public void setDriveEmbedLink(String driveEmbedLink) {
        this.driveEmbedLink = driveEmbedLink;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Question() {

    }
}
