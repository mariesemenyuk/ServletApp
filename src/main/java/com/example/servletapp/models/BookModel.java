package com.example.servletapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "book")
public class BookModel extends BaseModel{

    @Column(name = "publisher")
    private String publisher;
    @ManyToMany(mappedBy = "books")
    private List<UserModel> users;

    public BookModel() {
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
