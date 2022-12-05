package com.example.natour21.service;

import com.example.natour21.dao.InterestPointsDao;
import com.example.natour21.model.InterestPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertAndViewInterestPointsService {
    @Autowired
    InterestPointsDao interestPointsDao;
    public InsertAndViewInterestPointsService(@Qualifier("interestPoints") InterestPointsDao interestPointsDao) {
        this.interestPointsDao = interestPointsDao;
    }
    public Integer InsertInterestPoints(InterestPoints interestPoints) throws  Exception{
        return interestPointsDao.createInterestPoint(interestPoints);
    }
    public List<InterestPoints> getInterestPoint(Integer idPathway) throws Exception {
        return this.interestPointsDao.ViewInterestPoints(idPathway);
    }
}
