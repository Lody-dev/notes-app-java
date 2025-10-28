//package com.notes.notes_app.model;
//
//import jakarta.persistence.*;
//
//@Entity
//public class AppUser {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false, length = 25)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(nullable = false)
//    private String rol;
//
//    public AppUser() {
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRol() {
//        return null;
//    }
//
//    public void setRol(String rol) {
//        this.rol = rol;
//    }
//
//    public AppUser(Long id, String username, String password, String rol) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.rol = rol;
//    }
//}
