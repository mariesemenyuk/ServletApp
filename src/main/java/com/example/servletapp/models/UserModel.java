package com.example.servletapp.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "real_name")
    private String realName;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_vinyls",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "vinyl_id") })
    private Set<VinylModel> vinyls;

    public UserModel(int id, String username, String realName, String password) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.password = password;
    }

    public UserModel(String username, String realName, String password) {
        this.username = username;
        this.realName = realName;
        this.password = password;
    }

    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<VinylModel> getVinyls() {
        return vinyls;
    }

    public void setVinyls(Set<VinylModel> vinyls) {
        this.vinyls = vinyls;
    }
}
