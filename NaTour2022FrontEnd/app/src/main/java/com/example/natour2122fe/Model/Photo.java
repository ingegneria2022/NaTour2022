package com.example.natour2122fe.Model;


import androidx.annotation.NonNull;

public class Photo {

    private Integer id;

    private String name;

    private String username;

    private Integer idPathway;

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"name\":\"" + name +
                "\", \"username\": \"" + username  +
                "\", \"idPathway\":\"" + idPathway +
                "\"}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdPathway() {
        return idPathway;
    }

    public void setIdPathway(Integer idPathway) {
        this.idPathway = idPathway;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
