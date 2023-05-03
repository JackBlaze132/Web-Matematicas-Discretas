package com.co.MD.PPCTM.Repository;


import com.co.MD.PPCTM.Domain.EntityCancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPlayList extends JpaRepository<EntityCancion, Long> {

    EntityCancion findByTitulo(String titulo);
    EntityCancion findByPosicion(Integer posicion);

}
