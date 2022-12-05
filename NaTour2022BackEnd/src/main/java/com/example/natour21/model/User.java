package com.example.natour21.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

        @Id
        @Column(name = "username")
        @NotEmpty()
        public String username;


        @Column(name = "name")
        @NotEmpty()
        private String name;


        @Column(name = "surname")
        @NotEmpty()
        private String surname;


        @Column(name = "email")
        @NotEmpty()
        private String email;

        @Column(name = "photo")
        private String photo;

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                this.surname = surname;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoto() {return photo;}

        public void setPhoto(String photo) {this.photo = photo;}
}


