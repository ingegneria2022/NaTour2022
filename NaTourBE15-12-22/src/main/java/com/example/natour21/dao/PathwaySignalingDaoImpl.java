package com.example.natour21.dao;

import com.example.natour21.model.PathwaySignaling;
import com.example.natour21.model.RespondSignaling;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("pathwaySignaling")
public class PathwaySignalingDaoImpl implements PathwaySignalingDao{
    private final JdbcTemplate jdbcTemplate;
    public PathwaySignalingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Integer createSign(PathwaySignaling newPathwaySignaling) throws Exception {
        final String sql = "INSERT INTO `natour2022`.`pathwaySignaling` ( `title`, `description`, `username`,`idRespond`, `idPathway`) VALUES (?, ?, ?, ?, ?);";
        try{
            jdbcTemplate.update(sql, newPathwaySignaling.getTitle(), newPathwaySignaling.getDescriptionSign(), newPathwaySignaling.getUsername(), newPathwaySignaling.getIdRespond(), newPathwaySignaling.getIdPathway());
        } catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
    @Override
    public int ContPathwaySignaling(Integer idPathway) throws Exception {
        int contPathwaySignaling = 0;
        String sql = "SELECT * FROM natour2022.pathwaySignaling WHERE idPathway=?;";
        List<Map<String, Object>> righe;
        try {
            righe = jdbcTemplate.queryForList(sql, new Object[]{idPathway});
        }catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        for (Map<String, Object> riga : righe) {
            contPathwaySignaling=contPathwaySignaling+1;
        }
        return contPathwaySignaling;
    }

    @Override
    public List<PathwaySignaling> viewPathwaySignaling() throws Exception {
        List<PathwaySignaling> pathwaySignalings = new ArrayList<>();
        String sql = "SELECT * FROM natour2022.pathwaySignaling ;";
        List<Map<String, Object>> righe;
        try {
            righe = jdbcTemplate.queryForList(sql, new Object[]{});
        }catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        for (Map<String, Object> riga : righe) {
            PathwaySignaling pathwaySignaling = new PathwaySignaling();
            pathwaySignaling.setIdSign(((Integer) riga.get("idSign")));
            pathwaySignaling.setTitle((String) riga.get("title"));
            pathwaySignaling.setDescriptionSign(((String) riga.get("description")));
            pathwaySignaling.setIdPathway(((Integer) riga.get("idPathway")));
            pathwaySignaling.setIdRespond((Integer) riga.get("idRespond"));
            pathwaySignaling.setUsername((String) riga.get("username"));
            pathwaySignalings.add(pathwaySignaling);
        }
        return pathwaySignalings;
    }
    @Override
    public List<RespondSignaling> viewRespondSignaling(String user)  throws Exception {
        List<RespondSignaling> respondSignalings = new ArrayList<>();
        String sql = "SELECT * FROM natour2022.respondSignaling WHERE user=?;";
        List<Map<String, Object>> righe;
        try {
            righe = jdbcTemplate.queryForList(sql, new Object[]{user});
        }catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        for (Map<String, Object> riga : righe) {
            RespondSignaling respondSignaling = new RespondSignaling();
            respondSignaling.setDescriptionRisp(((String) riga.get("descriptionRisp")));
            respondSignaling.setIdRespond((Integer) riga.get("idRespond"));
            respondSignaling.setUsername((String) riga.get("user"));
            respondSignaling.setAdmin((String) riga.get("admin"));
            respondSignalings.add(respondSignaling);
        }
        return respondSignalings;
    }
    @Override
    public Integer createRespondSign(RespondSignaling newRespondSignaling) throws Exception {
            final String sql = "INSERT INTO `natour2022`.`respondSignaling` (`idRespond`, `descriptionRisp`, `admin`, `user`) VALUES (?, ?, ?, ?);";
            try{
                jdbcTemplate.update(sql,newRespondSignaling.getIdRespond(), newRespondSignaling.getDescriptionRisp(), newRespondSignaling.getAdmin(), newRespondSignaling.getUsername());
            } catch(DataAccessException e){
                throw new Exception(e.getMessage());
            }
        return null;
    }
    @Override
    public <S extends PathwaySignaling> S save(S entity) {
        return null;
    }
    @Override
    public <S extends PathwaySignaling> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }
    @Override
    public Optional<PathwaySignaling> findById(String s) {
        return Optional.empty();
    }
    @Override
    public boolean existsById(String s) {
        return false;
    }
    @Override
    public Iterable<PathwaySignaling> findAll() {
        return null;
    }
    @Override
    public Iterable<PathwaySignaling> findAllById(Iterable<String> strings) {
        return null;
    }
    @Override
    public long count() {
        return 0;
    }
    @Override
    public void deleteById(String s) {}
    @Override
    public void delete(PathwaySignaling entity) {}
    @Override
    public void deleteAllById(Iterable<? extends String> strings) {}
    @Override
    public void deleteAll(Iterable<? extends PathwaySignaling> entities) {}
    @Override
    public void deleteAll() {}
}
