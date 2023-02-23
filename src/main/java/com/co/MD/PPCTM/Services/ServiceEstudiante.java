package com.co.MD.PPCTM.Services;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import com.co.MD.PPCTM.Domain.EntityListaNodos;
import com.co.MD.PPCTM.Repository.RepositoryEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEstudiante {

    @Autowired
    RepositoryEstudiante repositoryEstudiante;

    @Autowired
    ServiceListaNodos serviceListaNodos;

    public Boolean insertarEstudianteJpa(EntityEstudiante estudiante){

        try{
            estudiante.setEntityListaNodos(serviceListaNodos.buscarListaNodosPorId(Long.parseLong("2")));
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
