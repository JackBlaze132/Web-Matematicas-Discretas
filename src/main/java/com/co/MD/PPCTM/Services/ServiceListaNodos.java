package com.co.MD.PPCTM.Services;

import com.co.MD.PPCTM.Domain.EntityListaNodos;
import com.co.MD.PPCTM.Repository.RepositoryListaNodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceListaNodos {

    @Autowired
    RepositoryListaNodos repositoryListaNodos;

    public Boolean insertarListaNodosJpa(EntityListaNodos listaNodos){

        try{
            repositoryListaNodos.save(listaNodos);
        }catch (Exception e){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public EntityListaNodos buscarListaNodosPorId(Long id){
        return repositoryListaNodos.findById(id).orElse(null);
    }
}
