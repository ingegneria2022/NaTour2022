package com.example.natour2122fe.Model;

import androidx.annotation.NonNull;

public class Feedback {

    private Integer id;
    private String description;
    private String vote;
    public Integer idPathway;
    public String username;

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "\"vote\":\"" + vote +
                "\", \"description\":\"" + description +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
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

