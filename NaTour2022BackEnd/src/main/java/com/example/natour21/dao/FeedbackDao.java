package com.example.natour21.dao;

import com.example.natour21.model.Feedback;
import com.example.natour21.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackDao extends CrudRepository<Feedback, String> {
    ResponseEntity<Object> createFeedback(Feedback newFeedback) throws Exception;
    List<Feedback> getFeedback(Integer username) throws Exception;
    List<Feedback> findAllByIdFeedbackEquals(String idFeedback);
    List<Feedback> findAllByUsernameEquals(User user);
    List<Feedback> ViewFeedback(String username, String vote, String description);
}
