package com.example.natour21.dao;
import com.example.natour21.model.InterestPoints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InterestPointsDao extends CrudRepository<InterestPoints, String> {
    Integer createInterestPoint(InterestPoints newInterestPoint) throws Exception;
    List<InterestPoints> ViewInterestPoints(Integer idPathway) throws Exception;
}