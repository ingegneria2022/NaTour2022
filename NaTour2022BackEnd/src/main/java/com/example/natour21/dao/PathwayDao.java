package com.example.natour21.dao;

import com.example.natour21.model.Pathway;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PathwayDao extends CrudRepository<Pathway, String> {
    List<Pathway> findByName(String name);
    List<Pathway> findByCity(String city);
    List<Pathway> findByDifficulty(String difficulty);
    List<Pathway> findByDuration(int duration);
    List<Pathway> findByAccessibily(String accessibility);
    Optional<Pathway> findById(int idPathway);
    boolean existsPathwayByIdEquals(Integer idPathway);
    List<Pathway> getAllPathway();
    Pathway findByid(int idPathway);
    Integer savePathway(Pathway pathway) throws Exception;
    void deletePathway(Pathway pathway);
    List<Pathway> ViewPathway(Integer idPathway) throws Exception;
    List<Pathway> viewAllPathway() throws Exception;
}
