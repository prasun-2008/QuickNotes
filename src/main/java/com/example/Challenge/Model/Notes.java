package com.example.Challenge.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String driveEmbedLink;

    public Notes(Long id, String title, String description, String driveEmbedLink) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.driveEmbedLink = driveEmbedLink;
    }

    public Notes() {

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
}

