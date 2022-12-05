package com.example.natour21.service;
import com.example.natour21.dao.PathwayDao;
import com.example.natour21.model.Pathway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ViewPathwayService {
    private final PathwayDao pathwayDao;
    @Autowired
    public ViewPathwayService(PathwayDao pathwayDao) {
        this.pathwayDao = pathwayDao;
    }
    public List<Pathway> ViewPathway(Integer idPathway) throws Exception {
        return this.pathwayDao.ViewPathway(idPathway);
    }
}