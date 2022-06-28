package com.example.servletapp.models;

import javax.persistence.*;

@Entity
@Table(name = "base")
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;

    public BaseModel() {
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
}
