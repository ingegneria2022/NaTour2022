package com.example.natour21.dao;

import com.amazonaws.services.s3.AmazonS3;
import com.example.natour21.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
@Repository("user")
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final AmazonS3 amazonS3Client;
    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate, AmazonS3 amazonS3Client) {
        this.jdbcTemplate = jdbcTemplate;
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public Integer addUser(User newUser) throws Exception {
        final String sql = "INSERT INTO `natour2022`.`user` (`name`, `surname`, `username`, `photo`, `email`) VALUES (?, ?, ?, ?, ?);";
        try{
            jdbcTemplate.update(sql, newUser.getName(), newUser.getSurname(), newUser.getUsername(), newUser.getPhoto(), newUser.getEmail());
        } catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
    @Override
    public User viewUser(String username) throws Exception {
        String sql = "SELECT * FROM `natour2022`.`user` WHERE `username`= ? ;";
        Map<String, Object> riga;
        try {
            riga = jdbcTemplate.queryForMap(sql, username);
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
        User user = new User();
            user.setName((String) riga.get("name"));
            user.setSurname((String) riga.get("surname"));
            user.setUsername((String) riga.get("username"));
            user.setPhoto((String) riga.get("photo"));
            user.setEmail((String) riga.get("email"));
        return user;
    }

    @Override
    public <S extends User> S save(S entity) {return null;}
    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {return null;}
    @Override
    public Optional<User> findById(String s) {return Optional.empty();}
    @Override
    public boolean existsById(String s) {return false;}
    @Override
    public Iterable<User> findAll() {return null;}
    @Override
    public Iterable<User> findAllById(Iterable<String> strings) {return null;}
    @Override
    public long count() {return 0;}
    @Override
    public void deleteById(String s) {}
    @Override
    public void delete(User entity) {}
    @Override
    public void deleteAllById(Iterable<? extends String> strings) {}
    @Override
    public void deleteAll(Iterable<? extends User> entities) {}
    @Override
    public void deleteAll() {}
    public JdbcTemplate getJdbcTemplate() {return jdbcTemplate;}
}
