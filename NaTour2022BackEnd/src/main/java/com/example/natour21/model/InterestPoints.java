package com.example.natour21.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "interestPoints")
public class InterestPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInterestPoints")
    private Integer idInterestPoints;

    @Column(name = "name")
    @NotEmpty()
    private String name;

    @Column(name = "type")
    @NotEmpty()
    private String type;

    @Column(name = "latitude")
    @NotEmpty()
    private Double latitude;

    @Column(name = "longitude")
    @NotEmpty()
    private Double longitude;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    public String username;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPathway")
    public Integer idPathway;

    public Integer getIdInterestPoints() {
        return idInterestPoints;
    }

    public void setIdInterestPoints(Integer idInterestPoints) {
        this.idInterestPoints = idInterestPoints;
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
