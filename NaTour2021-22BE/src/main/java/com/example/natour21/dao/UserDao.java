package com.example.natour21.dao;

import com.example.natour21.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao extends CrudRepository<User, String> {
    Integer addUser(User newUser) throws Exception;
    List<User> VisualizzaUtente(String username) throws Exception;
}
