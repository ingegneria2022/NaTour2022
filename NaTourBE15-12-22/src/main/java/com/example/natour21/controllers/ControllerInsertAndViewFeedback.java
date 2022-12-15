package com.example.natour21.controllers;

import com.example.natour21.dao.PathwayDao;
import com.example.natour21.dao.FeedbackDao;
import com.example.natour21.dao.UserDao;
import com.example.natour21.model.Feedback;
import com.example.natour21.service.InsertFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerInsertAndViewFeedback {
    private final FeedbackDao feedbackDao;
    private final UserDao userDao;
    private final PathwayDao pathwayDao;

    @Autowired
    InsertFeedbackService insertFeedbackService;


    public ControllerInsertAndViewFeedback(FeedbackDao feedbackDao, UserDao userDao, PathwayDao pathwayDao) {
        this.feedbackDao = feedbackDao;
        this.userDao = userDao;
        this.pathwayDao = pathwayDao;
    }

    @PostMapping("/all/addFeedback")
    public ResponseEntity<Object> addFeedback(@RequestBody Feedback newFeedback){

        try{
            return insertFeedbackService.InsertFeedbackPressed(newFeedback);

        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idPathway}/viewFeedback")
    public List<Feedback> ViewFeedback(@PathVariable("idPathway") Integer idPathway) throws Exception

    {
        List<Feedback> feedbacks;
        try{

             feedbacks = insertFeedbackService.getFeedback(idPathway);
        } catch(Exception e) {
            return null;
        }
        return feedbacks;
    }

}
