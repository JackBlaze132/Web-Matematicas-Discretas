package com.co.MD.PPCTM.Services;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import com.co.MD.PPCTM.Repository.RepositoryEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEstudiante {

    @Autowired
    RepositoryEstudiante repositoryEstudiante;

    public Boolean insertarEstudianteJpa(EntityEstudiante estudiante){

        try{
            repositoryEstudiante.save(estudiante);
        } catch (Exception e){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public List<EntityEstudiante> listarEstudiantes(){

        return repositoryEstudiante.findAll();
    }
}
