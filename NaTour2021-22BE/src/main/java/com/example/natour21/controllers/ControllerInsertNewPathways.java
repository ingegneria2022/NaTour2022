package com.example.natour21.controllers;

import com.example.natour21.dao.PathwayDao;
import com.example.natour21.model.Pathway;
import com.example.natour21.service.InsertPathwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerInsertNewPathways {
    private final PathwayDao pathwayDao;
    @Autowired
    InsertPathwayService insertPathwayService;

    public ControllerInsertNewPathways(PathwayDao pathwayDao) {
        this.pathwayDao = pathwayDao;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path = "/all/insertPathways", consumes = "application/json", produces = "application/json")
    public Object addPathway(@RequestBody Pathway newPathway) {
        try{
            insertPathwayService.InsertPathwayPressed(newPathway);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

