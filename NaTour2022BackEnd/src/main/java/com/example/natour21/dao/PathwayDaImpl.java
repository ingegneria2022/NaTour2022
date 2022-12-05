package com.example.natour21.dao;

import com.amazonaws.services.s3.AmazonS3;
import com.example.natour21.model.Pathway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository("pathway")
public class PathwayDaImpl implements PathwayDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PathwayDaImpl(JdbcTemplate jdbcTemplate, AmazonS3 amazonS3Client) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Pathway> findByName(String name) {
        return null;
    }

    @Override
    public List<Pathway> findByCity(String city) {
        return null;
    }

    @Override
    public List<Pathway> findByDifficulty(String difficulty) {
        return null;
    }

    @Override
    public List<Pathway> findByDuration(int duration) {
        return null;
    }

    @Override
    public List<Pathway> findByAccessibily(String accessibility) {
        return null;
    }

    @Override
    public Optional<Pathway> findById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean existsPathwayByIdEquals(Integer Id) {
        return false;
    }

    @Override
    public List<Pathway> getAllPathway() {
        return null;
    }

    @Override
    public Pathway findByid(int id) {
        return null;
    }

    @Override
    public Integer savePathway(Pathway newPathway) throws Exception {
        final String sql = "INSERT INTO `natour2022`.`pathway` ( `name`, `city`, `duration`, `difficulty`, `latStart`, `latFinish`, `description`, `accessibility`, `username`, `lngStart`, `lngFinish`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
               try{
            jdbcTemplate.update(sql, newPathway.getName(), newPathway.getCity(), newPathway.getDuration(), newPathway.getDifficulty(), newPathway.getLatStart(), newPathway.getLatFinish(), newPathway.getDescription(), newPathway.getAccessibility(), newPathway.getUsername(), newPathway.getLngStart(), newPathway.getLngFinish());
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return 1;
    }

    public List<Pathway> viewAllPathway() throws Exception {
        List<Pathway> pathways = new ArrayList<>();
        String sql = "SELECT * FROM natour2022.pathway ;";
        List<Map<String, Object>> righe;
        try {
            righe = jdbcTemplate.queryForList(sql);
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
        for (Map<String, Object> riga : righe) {
            Pathway pathwayVis = new Pathway();
            pathwayVis.setId((Integer) riga.get("idPathway"));
            pathwayVis.setName((String) riga.get("name"));
            pathwayVis.setCity((String) riga.get("city"));
            pathwayVis.setDuration((String) riga.get("duration"));
            pathwayVis.setDifficulty((String) riga.get("difficulty"));
            pathwayVis.setLatStart((Double) riga.get("latStart"));
            pathwayVis.setLatFinish((Double) riga.get("latFinish"));
            pathwayVis.setLngStart((Double) riga.get("lngStart"));
            pathwayVis.setLngFinish((Double) riga.get("lngFinish"));
            pathwayVis.setDescription((String) riga.get("description"));
            pathwayVis.setAccessibility((String) riga.get("accessibility"));
            pathwayVis.setUsername((String) riga.get("username"));
            pathways.add(pathwayVis);
        }
        return pathways;
    }
    @Override
    public List<Pathway> ViewPathway(Integer idPathway) throws Exception {
        List<Pathway> pathways = new ArrayList<>();
        String sql = "SELECT * FROM natour2022.pathway WHERE idPathway=?;";
        List<Map<String, Object>> righe;
        try {
            righe = jdbcTemplate.queryForList(sql, new Object[]{pathways});
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
        for (Map<String, Object> riga : righe) {
            Pathway pathway = new Pathway();
            pathway.setId((Integer) riga.get("idPathway"));
            pathway.setName((String) riga.get("name"));
            pathway.setCity((String) riga.get("city"));
            pathway.setDuration((String) riga.get("duration"));
            pathway.setDifficulty((String) riga.get("difficulty"));
            pathway.setLatStart((Double) riga.get("latStart"));
            pathway.setLatFinish((Double) riga.get("latFinish"));
            pathway.setLngStart((Double) riga.get("lngStart"));
            pathway.setLngFinish((Double) riga.get("lngFinish"));
            pathway.setDescription((String) riga.get("description"));
            pathway.setAccessibility((String) riga.get("accessibility"));
            pathway.setUsername((String) riga.get("username"));
            pathways.add(pathway);
        }
        return pathways;
    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    @Override
    public void deletePathway(Pathway pathway) {}
    @Override
    public <S extends Pathway> S save(S entity) {
        return null;
    }
    @Override
    public <S extends Pathway> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }
    @Override
    public Optional<Pathway> findById(String s) {
        return Optional.empty();
    }
    @Override
    public boolean existsById(String s) {
        return false;
    }
    @Override
    public Iterable<Pathway> findAll() {
        return null;
    }
    @Override
    public Iterable<Pathway> findAllById(Iterable<String> strings) {
        return null;
    }
    @Override
    public long count() {
        return 0;
    }
    @Override
    public void deleteById(String s) {}
    @Override
    public void delete(Pathway entity) {}
    @Override
    public void deleteAllById(Iterable<? extends String> strings) {}
    @Override
    public void deleteAll(Iterable<? extends Pathway> entities) {}
    @Override
    public void deleteAll() {}
}
