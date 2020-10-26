package com.example.demod.model.thucvat;


import javax.persistence.*;

@Entity
@Table(name = "user_infor")
public class UserInfor {
    private int id;

    private String username;

    private String pass;

    public UserInfor(int id, String username, String pass) {
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public UserInfor() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    @Column(name = "username", nullable = false)
    public String getUsername(){
        return this.username;
    }

    @Column(name = "pass", nullable = false)
    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "UserInfor{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
