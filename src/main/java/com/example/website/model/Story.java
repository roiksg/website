package com.example.website.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private String cover;
    private String description;
    private String genre;
    private LocalDateTime dateTimeCreate;
    private LocalDateTime dateTimeUpdate;

    public Story(String title, String cover, String description, String genre, LocalDateTime dateTimeCreate, LocalDateTime dateTimeUpdate, User user) {
        this.title = title;
        this.author = user;
        this.cover = cover;
        this.description = description;
        this.genre = genre;
        this.dateTimeCreate = dateTimeCreate;
        this.dateTimeUpdate = dateTimeUpdate;
    }

    public Story() {
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<NONE>";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LocalDateTime getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(LocalDateTime dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public LocalDateTime getDateTimeUpdate() {
        return dateTimeUpdate;
    }

    public void setDateTimeUpdate(LocalDateTime dateTimeUpdate) {
        this.dateTimeUpdate = dateTimeUpdate;
    }
}
