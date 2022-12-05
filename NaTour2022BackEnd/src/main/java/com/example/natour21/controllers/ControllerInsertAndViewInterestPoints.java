package com.example.natour21.controllers;


import com.example.natour21.dao.InterestPointsDao;
import com.example.natour21.dao.PathwayDao;
import com.example.natour21.dao.UserDao;
import com.example.natour21.model.InterestPoints;
import com.example.natour21.service.InsertAndViewInterestPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerInsertAndViewInterestPoints {
    private final InterestPointsDao interestPointsDao;
    private final UserDao userDao;
    private final PathwayDao pathwayDao;

    @Autowired
    InsertAndViewInterestPointsService insertAndViewInterestPointsService;



    public ControllerInsertAndViewInterestPoints(InterestPointsDao interestPointsDao, UserDao userDao, PathwayDao pathwayDao) {
        this.interestPointsDao = interestPointsDao;
        this.userDao = userDao;
        this.pathwayDao = pathwayDao;
    }

    @PostMapping("/all/addInterestPoints")
    public ResponseEntity<Object> addInterestPoint(@RequestBody InterestPoints newInterestPoint){

        try{
            insertAndViewInterestPointsService.InsertInterestPoints(newInterestPoint);

        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>("Punto Interesse Inserito", HttpStatus.OK);
    }

    @GetMapping("/{idPathway}/viewInterestPoints")
    public List<InterestPoints> ViewInterestPoints(@PathVariable("idPathway") Integer idPathway) throws Exception

    {
        List<InterestPoints> interestPoints;
        try{
            interestPoints = insertAndViewInterestPointsService.getInterestPoint(idPathway);
        } catch(Exception e) {
            return null;
        }
        return interestPoints;
    }
}
