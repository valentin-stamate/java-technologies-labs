package com.example.backend.database.models;

import com.example.backend.database.repositories.UserType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_files")
    public Set<Document> files;

    public User() { }

    public User(String username, UserType userType) {
        this.username = username;
        this.userType = userType;
    }

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<Document> getFiles() {
        return files;
    }

    public void setFiles(Set<Document> files) {
        this.files = files;
    }

    public void addFile(Document file) {
        if (this.files == null) {
            this.files = new HashSet<>();
        }

        this.files.add(file);
    }

    public void removeFile(Document document) {
        if (this.files == null) {
            this.files = new HashSet<>();
        }

        this.files.remove(document);
    }

}
