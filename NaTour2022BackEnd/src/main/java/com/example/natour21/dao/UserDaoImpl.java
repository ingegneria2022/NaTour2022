package com.example.natour21.dao;
import com.amazonaws.services.s3.AmazonS3;
import com.example.natour21.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
    public boolean existsByUsername(String username) {
        return false;
    }
    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
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
    public List<User> VisualizzaUtente(String username) throws Exception {
        List<User> utenti = new ArrayList<>();
        String sql = "SELECT * FROM natour2022.utente WHERE username=?;";
        List<Map<String, Object>> righe;
        try {
            righe = jdbcTemplate.queryForList(sql, new Object[]{username});
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
        for (Map<String, Object> riga : righe) {
            User user = new User();
            user.setName((String) riga.get("name"));
            user.setSurname((String) riga.get("surname"));
            user.setUsername((String) riga.get("username"));
            user.setPhoto((String) riga.get("photo"));
            user.setEmail((String) riga.get("email"));
            utenti.add(user);
        }
        return utenti;
    }
    @Override
    public List<String> getFotoUtente(User user) {return null;}
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
