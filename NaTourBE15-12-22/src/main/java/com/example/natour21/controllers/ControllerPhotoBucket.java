package com.example.natour21.controllers;

import com.example.natour21.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
public class ControllerPhotoBucket {
    @Autowired
    private PhotoService service;

    @PostMapping("/upload")
    public ResponseEntity uploadPhoto(@RequestParam(value = "photo") MultipartFile photo) {
        return new ResponseEntity<>(service.uploadPhoto(photo), HttpStatus.OK);
    }

    @GetMapping("/download/{photoName}")
    public ResponseEntity<ByteArrayResource> downloadPhoto(@PathVariable String photoName) {
        byte[] data = service.downloadPhoto(photoName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + photoName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{photoName}")
    public ResponseEntity<String> deletePhoto(@PathVariable String photoName) {
        return new ResponseEntity<>(service.deletePhoto(photoName), HttpStatus.OK);
    }
}
