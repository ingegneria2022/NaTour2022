package com.example.natour21.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Table(name = "pathway")
public class Pathway {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idPathway")
        private Integer idPathway;

        @Column(name = "name")

        @NotEmpty()
        private String name;

        @Column(name = "duration")
        @NotEmpty()
        private String duration;

        @Column(name = "difficulty")
        @NotEmpty()
        private String difficulty;

        @Column(name = "latStart")
        @NotEmpty()
        private Double latStart;

        @Column(name = "latFinish")
        @NotEmpty()
        private Double latFinish;

        @Column(name = "lngStart")
        @NotEmpty()
        private Double lngStart;

        @Column(name = "lngFinish")
        @NotEmpty()
        private Double lngFinish;

        @Column(name = "description")
        @NotEmpty()
        private String description;

        @Column(name = "accessibility")
        @NotEmpty()
        private String accessibility;

        @Column(name = "city")
        @NotEmpty()
        private String city;

        @NotNull
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "username")
        public String username;


        public Pathway() {
                this.idPathway =idPathway;
                this.name = name;
                this.description = description;
                this.city = city;
                this.accessibility = accessibility;
                this.latFinish = latFinish;
                this.latStart = latStart;
                this.difficulty = difficulty;
                this.lngFinish = lngFinish;
                this.lngStart = lngStart;
        }

        public @NotEmpty() Double getLngStart() {
                return lngStart;
        }

        public void setLngStart(@NotEmpty() Double lngStart) {
                this.lngStart = lngStart;
        }

        public @NotEmpty() Double getLngFinish() {
                return lngFinish;
        }

        public void setLngFinish(@NotEmpty() Double lngFinish) {
                this.lngFinish = lngFinish;
        }

        /*public List<String> getFoto() {
                return foto;
        }

        public void setFoto(List<String> foto) {
                this.foto = foto;
        }*/

        public Integer getId() {
                return idPathway;
        }

        public void setId(Integer idPathway) {
                this.idPathway = idPathway;
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


        public @NotEmpty() Double getLatStart() {
                return latStart;
        }

        public void setLatStart(@NotEmpty() Double latStart) {
                this.latStart = latStart;
        }

        public @NotEmpty() Double getLatFinish() {
                return latFinish;
        }

        public void setLatFinish(@NotEmpty() Double latFinish) {
                this.latFinish = latFinish;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getDifficulty() {
                return difficulty;
        }

        public void setDifficulty(String difficulty) {
                this.difficulty = difficulty;
        }

        public String getAccessibility() {
                return accessibility;
        }

        public void setAccessibility(String accessibility) {
                this.accessibility = accessibility;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername( String username) {
                this.username = username;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String citta) {
                this.city = citta;
        }




}
