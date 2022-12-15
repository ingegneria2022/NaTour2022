package com.example.natour21.dao;

import com.example.natour21.model.Pathway;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PathwayDao extends CrudRepository<Pathway, String> {
    Integer savePathway(Pathway pathway) throws Exception;
    List<Pathway> ViewPathway(Integer idPathway) throws Exception;
    List<Pathway> viewAllPathway() throws Exception;
}
