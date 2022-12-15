package com.example.natour2122fe.Model;

import java.io.Serializable;
import java.util.List;

public class Pathway implements Serializable {

    private Integer id;

    private String name;

    private String duration;

    private String difficulty;

    private Double lngStart;

    private Double lngFinish;

    private Double latStart;

    private Double latFinish;

    private String description;

    private String accessibility;

    private String city;

    public String username;

    private List<Feedback> feedback;

    private List<InterestPoints> interestPoints;


    private List<PathwaySignaling> pathwaySignalings;

    public Pathway() {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.city = city;
        this.accessibility = accessibility;
        this.lngFinish = lngFinish;
        this.lngStart = lngStart;
        this.latFinish = latFinish;
        this.latStart = latStart;
        this.difficulty = difficulty;
        this.feedback = feedback;
        this.interestPoints = interestPoints;
        this.pathwaySignalings = pathwaySignalings;

    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name +
                "\", \"city\":\"" + city +
                "\", \"duration\":\"" + duration +
                "\", \"difficulty\":\"" + difficulty  +
                "\", \"latStart\":" + latStart  +
                ", \"latFinish\":\"" + latFinish  +
                ", \"description\":\"" + description  +
                "\", \"accessibility\":\"" + accessibility  +
                "\", \"username\":\"" + username  +
                "\", \"lngStart\":\"" + lngStart  +
                ", \"lngFinish\":\"" + lngFinish  +
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Double getLngStart() {
        return lngStart;
    }

    public void setLngStart(Double lngStart) {
        this.lngStart = lngStart;
    }

    public Double getLngFinish() {
        return lngFinish;
    }

    public void setLngFinish(Double lngFinish) {
        this.lngFinish = lngFinish;
    }

    public Double getLatStart() {
        return latStart;
    }

    public void setLatStart(Double latStart) {
        this.latStart = latStart;
    }

    public Double getLatFinish() {
        return latFinish;
    }

    public void setLatFinish(Double latFinish) {
        this.latFinish = latFinish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }

    public List<InterestPoints> getInterestPoints() {
        return interestPoints;
    }

    public void setInterestPoints(List<InterestPoints> interestPoints) {
        this.interestPoints = interestPoints;
    }

    public List<PathwaySignaling> getPathwaySignalings() {
        return pathwaySignalings;
    }

    public void setPathwaySignalings(List<PathwaySignaling> pathwaySignalings) {
        this.pathwaySignalings = pathwaySignalings;
    }
}

