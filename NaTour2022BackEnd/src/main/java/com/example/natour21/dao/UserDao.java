package com.example.natour21.dao;
import com.example.natour21.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserDao extends CrudRepository<User, String> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Integer addUser(User newUser) throws Exception;
    List<User> VisualizzaUtente(String username) throws Exception;
    List<String> getFotoUtente(User user);
}
