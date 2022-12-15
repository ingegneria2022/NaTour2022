package com.example.natour21.service;
import com.example.natour21.dao.PathwayDao;
import com.example.natour21.model.Pathway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class InsertPathwayService {
    @Autowired
    PathwayDao pathwayDao;
    public InsertPathwayService(@Qualifier("pathway") PathwayDao pathwayDao) {
        this.pathwayDao = pathwayDao;
    }
    public Integer InsertPathwayPressed(Pathway newPathway) throws Exception {
        return pathwayDao.savePathway(newPathway);
    }
}