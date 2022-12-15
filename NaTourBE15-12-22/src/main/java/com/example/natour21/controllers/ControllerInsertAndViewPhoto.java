package com.example.natour21.controllers;

import com.example.natour21.dao.PathwayDao;
import com.example.natour21.dao.UserDao;
import com.example.natour21.model.Photo;
import com.example.natour21.service.InsertAndViewPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class ControllerInsertAndViewPhoto {
    private final PathwayDao pathwayDao;
    private final UserDao userDao;
    @Autowired
    InsertAndViewPhotoService insertAndViewPhotoService;

    public ControllerInsertAndViewPhoto(UserDao userDao, PathwayDao pathwayDao) {
        this.userDao = userDao;
        this.pathwayDao = pathwayDao;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/all/addPhoto")
    public ResponseEntity addPhoto(@RequestBody Photo newPhoto){

        try{
            insertAndViewPhotoService.InsertPhotoPressed(newPhoto);

        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity("photo inserita", HttpStatus.OK);
    }
    @GetMapping("/{idPathway}/viewPhoto")
    public List<Photo> ViewPhoto(@PathVariable("idPathway") Integer idPathway) throws Exception
    {
        List<Photo> photos;
        try{
            photos = insertAndViewPhotoService.getPhoto(idPathway);
        } catch(Exception e) {
            return null;
        }
        return photos;
    }
}
