package com.example.natour21.dao;

import com.example.natour21.model.PathwaySignaling;
import com.example.natour21.model.RespondSignaling;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PathwaySignalingDao extends CrudRepository<PathwaySignaling, String> {
    Integer createSign(PathwaySignaling newPathwaySignaling) throws Exception;
    int ContPathwaySignaling(Integer idPathway) throws Exception;
    List<PathwaySignaling> viewPathwaySignaling() throws Exception;
    List<RespondSignaling> viewRespondSignaling(String username) throws Exception;
    Integer createRespondSign(RespondSignaling newRespondSignaling) throws Exception;
}
