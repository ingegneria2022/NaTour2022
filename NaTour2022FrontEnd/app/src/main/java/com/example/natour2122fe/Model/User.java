package com.example.natour2122fe.Model;

import android.net.Uri;

import androidx.annotation.NonNull;

public class User {

    public String username;

    private String name;

    private String surname;

    private String email;

    private String photo;

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name +
                "\", \"surname\":\"" + surname +
                "\", \"username\": \"" + username  +
                "\", \"email\":\"" + email +
                "\", \"photo\":\"" + photo +
                "\"}";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
