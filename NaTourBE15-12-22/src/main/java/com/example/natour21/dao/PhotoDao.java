package com.example.natour21.dao;
import com.example.natour21.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PhotoDao extends CrudRepository<Photo, String> {
    Integer createPhoto(Photo newPhoto) throws Exception ;
    List<Photo> ViewPhoto(Integer idPathway) throws Exception;
}
