package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.websocket.ClientEndpoint;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", unique = true)
//    @NotEmpty(message = "username is required")
    private String username;

    @Column(name = "password")
//    @NotEmpty(message = "password is required")
    private String password;

    @Column(name = "email")
//    @NotEmpty(message = "email is required")
    private String email;

    @Column(name = "role")
//    @NotEmpty(message = "role is required")
    private String role;

    public User() {}

    public User(long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
