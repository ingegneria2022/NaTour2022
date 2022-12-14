package com.example.natour21.dao;

import com.example.natour21.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("feedback")
public class FeedbackDaoImpl implements FeedbackDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public FeedbackDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public ResponseEntity<Object> createFeedback(Feedback newFeedback) throws Exception {
        final String sql = "INSERT INTO `natour2022`.`feedback` (`vote`, `description`, `username`, `idPathway`) VALUES (?, ?, ?, ?);";
        try{
            jdbcTemplate.update(sql, newFeedback.getVote(), newFeedback.getDescription(), newFeedback.getUsername(), newFeedback.getIdPathway());
        } catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Feedback> getFeedback(Integer idPathway) throws Exception {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM natour2022.feedback WHERE idPathway=?;";
        List<Map<String, Object>> righe;
        try {
           righe = jdbcTemplate.queryForList(sql, new Object[]{idPathway});
        }catch(DataAccessException e){
            throw new Exception(e.getMessage());
        }
        for (Map<String, Object> riga : righe) {
            Feedback feedback = new Feedback();
            feedback.setIdFeedback(((Integer) riga.get("idFeedback")));
            feedback.setDescription((String) riga.get("description"));
            feedback.setVote(((String) riga.get("vote")));
            feedback.setUsername((String) riga.get("username"));
            feedback.setIdPathway(((Integer) riga.get("idPathway")));
            feedbacks.add(feedback);
        }
        return feedbacks;
    }

    @Override
    public List<Feedback> ViewFeedback(String username, String vote, String description) {
        return null;
    }
    @Override
    public <S extends Feedback> S save(S entity) {
        return null;
    }
    @Override
    public <S extends Feedback> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }
    @Override
    public Optional<Feedback> findById(String s) {
        return Optional.empty();
    }
    @Override
    public boolean existsById(String s) {
        return false;
    }
    @Override
    public Iterable<Feedback> findAll() {
        return null;
    }
    @Override
    public Iterable<Feedback> findAllById(Iterable<String> strings) {
        return null;
    }
    @Override
    public long count() {
        return 0;
    }
    @Override
    public void deleteById(String s) {}
    @Override
    public void delete(Feedback entity) {}
    @Override
    public void deleteAllById(Iterable<? extends String> strings) {}
    @Override
    public void deleteAll(Iterable<? extends Feedback> entities) {}
    @Override
    public void deleteAll() {}
}