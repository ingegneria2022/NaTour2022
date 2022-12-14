package com.example.natour21.dao;

import com.example.natour21.model.InterestPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("interestPoints")
public class InterestPointsDaoImpl implements InterestPointsDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public InterestPointsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Integer createInterestPoint(InterestPoints newInterestPoint) throws Exception {
        final String sql = "INSERT INTO `natour2022`.`interestPoints` (`idInterestPoints`, `name`, `type`, `latitude`, `longitude`,`username`,`idPathway`) VALUES (?, ?, ?, ?, ?,?,?);";
        try{
            jdbcTemplate.update(sql, newInterestPoint.getIdInterestPoints(), newInterestPoint.getName(), newInterestPoint.getType(), newInterestPoint.getLatitude(), newInterestPoint.getLongitude(), newInterestPoint.getUsername(), newInterestPoint.getIdPathway());
        } catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
    @Override
    public List<InterestPoints> ViewInterestPoints(Integer idPathway) throws Exception {
        List<InterestPoints> interestsPoints = new ArrayList<>();
        String sql = "SELECT * FROM natour2022.interestPoints WHERE idPathway=?;";
        List<Map<String, Object>> righe;
        try {
            righe = jdbcTemplate.queryForList(sql, new Object[]{idPathway});
        }catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        for (Map<String, Object> riga : righe) {
            InterestPoints interestPoints = new InterestPoints();
            interestPoints.setIdInterestPoints(((Integer) riga.get("idInterestPoints")));
            interestPoints.setName((String) riga.get("name"));
            interestPoints.setType(((String) riga.get("type")));
            interestPoints.setUsername((String) riga.get("username"));
            interestPoints.setLatitude((Double) riga.get("latitude"));
            interestPoints.setLongitude((Double) riga.get("longitude"));
            interestPoints.setIdPathway((Integer) riga.get("idPathway"));
            interestsPoints.add(interestPoints);
        }
        return interestsPoints;
    }
    @Override
    public <S extends InterestPoints> S save(S entity) {
        return null;
    }
    @Override
    public <S extends InterestPoints> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }
    @Override
    public Optional<InterestPoints> findById(String s) {
        return Optional.empty();
    }
    @Override
    public boolean existsById(String s) {
        return false;
    }
    @Override
    public Iterable<InterestPoints> findAll() {
        return null;
    }
    @Override
    public Iterable<InterestPoints> findAllById(Iterable<String> strings) {
        return null;
    }
    @Override
    public long count() {
        return 0;
    }
    @Override
    public void deleteById(String s) {}
    @Override
    public void delete(InterestPoints entity) {}
    @Override
    public void deleteAllById(Iterable<? extends String> strings) {}
    @Override
    public void deleteAll(Iterable<? extends InterestPoints> entities) {}
    @Override
    public void deleteAll() {}
}