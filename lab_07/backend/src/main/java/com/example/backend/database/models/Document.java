package com.example.backend.database.models;

import jakarta.persistence.*;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String documentId;

    @Column
    private String name;

    @Column
    private Long size;

    @Column
    private String author;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Lob
    @Column
    private byte[] file;

    public Document() { }

    public Document(String name, String author, byte[] buffer, long size, User user) {
        this.name = name;
        this.author = author;
        this.file = buffer;
        this.size = size;
        this.user = user;
        this.documentId = RandomStringUtils.random(32, true, true);
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

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
