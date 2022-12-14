package com.example.natour21.dao;

import com.amazonaws.services.s3.AmazonS3;
import com.example.natour21.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("photo")
public class PhotoDaoImpl implements PhotoDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PhotoDaoImpl(JdbcTemplate jdbcTemplate, AmazonS3 amazonS3Client) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Integer createPhoto(Photo newPhoto) throws Exception {
        final String sql = "INSERT INTO `natour2022`.`photo` (`id`, `name`, `username`, `idPathway`) VALUES (?, ?, ?, ?);";
        try{
            jdbcTemplate.update(sql, newPhoto.getId(), newPhoto.getName(),  newPhoto.getUsername(), newPhoto.getIdPathway());
        } catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Photo> ViewPhoto(Integer idPathway) throws Exception {
        List<Photo> photos = new ArrayList<>();
        String sql = "SELECT * FROM natour2022.photo WHERE idPathway=?;";
        List<Map<String, Object>> righe;
        try {
            righe = jdbcTemplate.queryForList(sql, new Object[]{idPathway});
        }catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        for (Map<String, Object> riga : righe) {
            Photo photo = new Photo();
            photo.setId(((Integer) riga.get("id")));
            photo.setName((String) riga.get("name"));
            photo.setUsername((String) riga.get("username"));
            photo.setIdPathway((Integer) riga.get("idPathway"));
            photos.add(photo);
        }
        return photos;
    }
    @Override
    public <S extends Photo> S save(S entity) {
        return null;
    }
    @Override
    public <S extends Photo> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }
    @Override
    public Optional<Photo> findById(String s) {
        return Optional.empty();
    }
    @Override
    public boolean existsById(String s) {
        return false;
    }
    @Override
    public Iterable<Photo> findAll() {
        return null;
    }
    @Override
    public Iterable<Photo> findAllById(Iterable<String> strings) {
        return null;
    }
    @Override
    public long count() {
        return 0;
    }
    @Override
    public void deleteById(String s) {}
    @Override
    public void delete(Photo entity) {}
    @Override
    public void deleteAllById(Iterable<? extends String> strings) {}
    @Override
    public void deleteAll(Iterable<? extends Photo> entities) {}
    @Override
    public void deleteAll() {}
}