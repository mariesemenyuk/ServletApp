package com.example.servletapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cd")
public class CdModel extends BaseModel{
    @Column(name = "year_issued")
    private int yearIssued;

    @ManyToMany(mappedBy = "cds")
    private List<UserModel> users;

    public CdModel() {
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public int getYearIssued() {
        return yearIssued;
    }

    public void setYearIssued(int yearIssued) {
        this.yearIssued = yearIssued;
    }
}
