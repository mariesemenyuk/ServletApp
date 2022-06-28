package com.example.servletapp.models;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vinyl")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VinylModel extends BaseModel{

    @Column(name = "country_issued")
    private String countryIssued;
    @Column(name = "price")
    private int price;

    @ManyToMany(mappedBy = "vinyls")
    private List<UserModel> users;

    public VinylModel() {
    }

    public String getCountryIssued() {
        return countryIssued;
    }

    public void setCountryIssued(String countryIssued) {
        this.countryIssued = countryIssued;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
