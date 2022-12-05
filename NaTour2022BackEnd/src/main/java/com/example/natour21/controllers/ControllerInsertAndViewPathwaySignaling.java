package com.example.natour21.controllers;
import com.example.natour21.dao.PathwaySignalingDao;
import com.example.natour21.model.PathwaySignaling;
import com.example.natour21.model.RespondSignaling;
import com.example.natour21.service.InsertAndViewPathwaySignalingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ControllerInsertAndViewPathwaySignaling {


    private final PathwaySignalingDao pathwaySignalingDao;
    @Autowired
    InsertAndViewPathwaySignalingService insertAndViewPathwaySignalingService;

    public ControllerInsertAndViewPathwaySignaling(PathwaySignalingDao pathwaySignalingDao) {
        this.pathwaySignalingDao = pathwaySignalingDao;
    }

    @PostMapping("/all/addPathwaySignaling")
    public ResponseEntity<Object> addPathwaySignaling(@RequestBody PathwaySignaling newPathwaySignaling){
        try{
            insertAndViewPathwaySignalingService.InsertSign(newPathwaySignaling);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Segnalazione Inserita", HttpStatus.OK);
    }

    @PostMapping("/all/addRespondSignaling")
    public ResponseEntity<Object> addRespondSignaling(@RequestBody RespondSignaling newRespondSignaling){
        try{
            insertAndViewPathwaySignalingService.InsertRespSign(newRespondSignaling);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Risposta Segnalazione Inserita", HttpStatus.OK);
    }

    @GetMapping("/{idPathway}/contPathwaySignaling")
    public Integer ContPathwaySignaling(@PathVariable("idPathway") Integer idPathway) throws Exception
    {
        int contPathwaySignaling;
        try{
            contPathwaySignaling = insertAndViewPathwaySignalingService.getPathwaySignaling(idPathway);
        } catch(Exception e) {
            return 0;
        }
        return contPathwaySignaling;
    }

    @GetMapping("/viewPathwaysSign")
    public List<PathwaySignaling> ViewSign() throws Exception
    {
        List<PathwaySignaling> pathwaySignalings;
        try{
            pathwaySignalings = insertAndViewPathwaySignalingService.getPathwaySign();
        } catch(Exception e) {
            return null;
        }
        return pathwaySignalings;
    }

    @GetMapping("/{username}/viewRespondPathwaySign")
    public List<RespondSignaling> ViewRespondSign(@PathVariable("username") String username) throws Exception
    {
        List<RespondSignaling> respondSignalings;
        try{
            respondSignalings = insertAndViewPathwaySignalingService.getRespondSign(username);
        } catch(Exception e) {
            return null;
        }
        return respondSignalings;
    }

}
