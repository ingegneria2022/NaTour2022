package com.example.natour2122fe.Model;

import java.io.Serializable;

public class PathwaySignaling implements Serializable {

    private long id;

    private String title;

    private String descriptionSign;

    public Integer idPathway;

    public String username;


    @Override
    public String toString() {
        return "{" +
                "\"title\":\"" + title+
                "\", \"descriptionSign\":\"" + descriptionSign +
                "\", \"username\":\"" + username  +
                "\", \"idPathway\":\"" + idPathway  +
                "\"}";
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionSign() {return descriptionSign;}

    public void setDescriptionSign(String descriptionSign) {this.descriptionSign = descriptionSign;}

    public Integer getIdPathway() {return idPathway;}

    public void setIdPathway(Integer idPathway) {this.idPathway = idPathway;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}
}

