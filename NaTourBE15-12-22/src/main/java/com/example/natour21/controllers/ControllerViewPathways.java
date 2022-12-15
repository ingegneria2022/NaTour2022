package com.example.natour21.controllers;

import com.example.natour21.dao.PathwayDao;
import com.example.natour21.model.Pathway;
import com.example.natour21.service.ViewPathwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerViewPathways {
    private final PathwayDao pathwayDao;
    @Autowired
    ViewPathwayService viewPathwayService;
    public ControllerViewPathways(PathwayDao pathwayDao) {
        this.pathwayDao = pathwayDao;
    }
    @GetMapping("/{idPathway}/viewPathway")
    public List<Pathway> ViewPathway(@PathVariable("idPathway") Integer idPathway) throws Exception
    {
        return viewPathwayService.ViewPathway(idPathway);
    }
    @GetMapping("/all/viewPathway")
    public List<Pathway> ViewPathways()
    {
        try {
            final List<Pathway> pathwayList = pathwayDao.viewAllPathway();
            return pathwayList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
