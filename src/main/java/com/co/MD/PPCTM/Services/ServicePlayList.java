package com.co.MD.PPCTM.Services;

import com.co.MD.PPCTM.Domain.EntityCancion;
import com.co.MD.PPCTM.Repository.RepositoryPlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePlayList {

    @Autowired
    RepositoryPlayList repositoryPlayList;

    private Boolean validaciones(EntityCancion cancion){

        if(cancion == null){
            return false;
        }
        if(cancion.getNumSegundos() < 0 || cancion.getNumSegundos() > 59){
            return false;
        }
        if(cancion.getNumMinutos() < 0 || cancion.getNumMinutos() > 59){
            return false;
        }
        return cancion.getNumSegundos() != 0 || cancion.getNumMinutos() != 0;
    }


    public Boolean agregarCancion(EntityCancion cancion){

        if(!validaciones(cancion)){
            return Boolean.FALSE;
        }
        else{
            String titulo = cancion.getTitulo();
            String autor = cancion.getAutor();
            String genero = cancion.getGenero();
            Long minutos = cancion.getNumMinutos();
            Long segundos = cancion.getNumSegundos();
            repositoryPlayList.save(new EntityCancion(titulo, autor, genero, minutos, segundos));
        }
        return true;
    }

    public Boolean eliminarCancion(EntityCancion cancion){

        try{
            EntityCancion buscada = repositoryPlayList.findByTitulo(cancion.getTitulo());
            repositoryPlayList.deleteById(buscada.getId());
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean editarCancion(EntityCancion pCancion){

        try{
            Optional<EntityCancion> buscado = repositoryPlayList.findById(pCancion.getId());
            EntityCancion cancion = buscado.get();
            cancion.setTitulo(pCancion.getTitulo());
            cancion.setAutor(pCancion.getAutor());
            cancion.setGenero(pCancion.getGenero());
            cancion.setDuracion(pCancion.getDuracion());
            repositoryPlayList.save(cancion);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<EntityCancion> listarCanciones(){
        return repositoryPlayList.findAll();
    }
}
