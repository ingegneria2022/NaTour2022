package com.example.natour21.service;

import com.example.natour21.dao.FeedbackDao;
import com.example.natour21.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertFeedbackService {
    @Autowired
    FeedbackDao feedbackDao;
    public InsertFeedbackService(@Qualifier("feedback") FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }
    public ResponseEntity<Object> InsertFeedbackPressed(Feedback newFeedback) throws  Exception{
        return feedbackDao.createFeedback(newFeedback);
    }

    public List<Feedback> ViewFeedback(String username, String vote, String description) {
        return this.feedbackDao.ViewFeedback(username, vote, description);
    }

    public List<Feedback> getFeedback(Integer idPathway) throws Exception{
        return this.feedbackDao.getFeedback(idPathway);
    }
}
