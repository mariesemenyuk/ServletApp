package com.example.servletapp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vinyl")
public class VinylModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "vinyls")
    private List<UserModel> users;

    public VinylModel() {
    }

    public VinylModel(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public VinylModel(int id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
