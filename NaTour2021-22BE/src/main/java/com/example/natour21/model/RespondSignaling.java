package com.example.natour21.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "respondSignaling")
public class RespondSignaling {

    @Id
    @Column(name = "idRespond")
    @NotEmpty()
    private Integer idRespond;

    @Column(name = "descriptionRisp")
    @NotEmpty()
    private String descriptionRisp;

    @Column(name = "admin")
    @NotEmpty()
    public String admin;

    @Column(name = "user")
    @NotEmpty()
    public String username;

    public Integer getIdRespond() {
        return idRespond;
    }

    public void setIdRespond(Integer idRespond) {
        this.idRespond = idRespond;
    }

    public String getDescriptionRisp() {
        return descriptionRisp;
    }

    public void setDescriptionRisp(String descriptionRisp) {
        this.descriptionRisp = descriptionRisp;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
