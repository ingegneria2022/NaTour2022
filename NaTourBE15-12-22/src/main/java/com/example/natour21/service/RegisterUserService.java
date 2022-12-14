package com.example.natour21.service;

import com.example.natour21.dao.UserDao;
import com.example.natour21.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class RegisterUserService {
    @Autowired
    UserDao userDao;
    public RegisterUserService(@Qualifier("user") UserDao userDao) {
        this.userDao = userDao;}
    public Integer addUser( User newUser) throws Exception {
        return userDao.addUser(newUser);
    }
    public User viewUser(String username) throws Exception{
        return this.userDao.viewUser(username);
    }
}