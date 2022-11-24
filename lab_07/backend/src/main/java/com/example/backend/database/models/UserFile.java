package com.example.backend.database.models;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class UserFile {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String author;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Lob
    @Column
    private byte[] file;

    public UserFile() { }

    public UserFile(String name, String author, byte[] buffer, User user) {
        this.name = name;
        this.author = author;
        this.file = buffer;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
