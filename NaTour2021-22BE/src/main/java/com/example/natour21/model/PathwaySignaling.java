package com.example.natour21.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "pathwaySignaling")
public class PathwaySignaling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSign")
    private Integer idSign;

    @Column(name = "title")
    @NotEmpty()
    private String title;

    @Column(name = "description")
    @NotEmpty()
    private String descriptionSign;

   @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPathway")
    public Integer idPathway;


    @Column(name = "idRespond")
    public Integer idRespond;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    public String username;

    public void setIdSign(Integer idSign) {
        this.idSign = idSign;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionSign() {
        return descriptionSign;
    }

    public void setDescriptionSign(String descriptionSign) {
        this.descriptionSign = descriptionSign;
    }

    public Integer getIdPathway() {
        return idPathway;
    }

    public void setIdPathway(Integer idPathway) {
        this.idPathway = idPathway;
    }

    public Integer getIdRespond() {
        return idRespond;
    }

    public void setIdRespond(Integer idRespond) {
        this.idRespond = idRespond;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
