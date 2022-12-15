package com.example.natour21.controllers;

import com.example.natour21.dao.UserDao;
import com.example.natour21.model.User;
import com.example.natour21.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerRegisterAndViewUser {
    @Qualifier("userDao")
    private final UserDao userDao;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    RegisterUserService registerUserService;

    public ControllerRegisterAndViewUser(UserDao userDao, JdbcTemplate jdbcTemplate) {
        this.userDao = userDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path = "/all/addUser", consumes = "application/json", produces = "application/json")
    public Object addUser(@RequestBody User newUser) {
        try{
            registerUserService.addUser(newUser);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Caricato", HttpStatus.OK);
    }

    @GetMapping("/{username}/viewUser")
    public User viewUser(@PathVariable("username") String username) throws Exception{
        return registerUserService.viewUser(username);
    }
}
