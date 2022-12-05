package com.example.natour21.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "photo")

public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty()
    private String name;

    @NotNull
    @Column(name = "idPathway")
    private Integer idPathway;

    @NotNull
    @Column(name = "username")
    public String username;

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