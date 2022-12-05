package com.example.natour21.service;

import com.example.natour21.dao.PathwaySignalingDao;
import com.example.natour21.model.PathwaySignaling;
import com.example.natour21.model.RespondSignaling;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertAndViewPathwaySignalingService {
    private final PathwaySignalingDao pathwaySignalingDao;

    public InsertAndViewPathwaySignalingService(@Qualifier("pathwaySignaling") PathwaySignalingDao pathwaySignalingDao) {
        this.pathwaySignalingDao = pathwaySignalingDao;

    }
    public Integer InsertSign(PathwaySignaling pathwaySignaling) throws Exception {

        return pathwaySignalingDao.createSign(pathwaySignaling);
    }

    public Integer InsertRespSign(RespondSignaling newRespondSignaling) throws Exception {
        return pathwaySignalingDao.createRespondSign(newRespondSignaling);
    }
    public int getPathwaySignaling(Integer idPathway) throws Exception {
        return this.pathwaySignalingDao.ContPathwaySignaling(idPathway);
    }

    public List<PathwaySignaling> getPathwaySign() {
        try {
            return this.pathwaySignalingDao.viewPathwaySignaling();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<RespondSignaling> getRespondSign(String username) {
        try {
            return this.pathwaySignalingDao.viewRespondSignaling(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}




