package com.example.natour21.service;

import com.example.natour21.dao.PhotoDao;
import com.example.natour21.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertAndViewPhotoService {
    @Autowired
    PhotoDao photoDao;

    public InsertAndViewPhotoService(@Qualifier("photo") PhotoDao photoDao) {
        this.photoDao = photoDao;
    }
    public Integer InsertPhotoPressed(Photo newPhoto) throws  Exception{
        return photoDao.createPhoto(newPhoto);
    }
    public List<Photo> getPhoto(Integer idPathway) throws Exception {
            return this.photoDao.ViewPhoto(idPathway);
    }
}
