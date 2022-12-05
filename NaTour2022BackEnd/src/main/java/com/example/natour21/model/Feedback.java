package com.example.natour21.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "feedback")

public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFeedback")
    private Integer idFeedback;

    @Column(name = "description")
    @NotEmpty()
    private String description;

    @Column(name = "vote")
    @NotEmpty()
    private String vote;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPathway")
    public Integer idPathway;


    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    public String username;


    public Integer getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(Integer idFeedback) {
        this.idFeedback = idFeedback;
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
