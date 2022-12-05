package com.example.natour2122fe.Model;

public class InterestPoints {

    private Integer id;

    private String name;

    private String type;

    private Double latitude;

    private Double longitude;

    public String username;

    public Integer idPathway;

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name +
                "\", \"type\":\"" + type  +
                "\", \"latitude\":" + latitude  +
                ", \"longitude\":\"" + longitude  +
                "\", \"username\":\"" + username  +
                "\", \"idPathway\":\"" + idPathway  +
                "}";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdPathway() {
        return idPathway;
    }

    public void setIdPathway(Integer idPathway) {
        this.idPathway = idPathway;
    }
}
