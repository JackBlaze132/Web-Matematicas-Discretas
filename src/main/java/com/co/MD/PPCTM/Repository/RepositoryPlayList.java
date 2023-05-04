package com.co.MD.PPCTM.Repository;


import com.co.MD.PPCTM.Domain.EntityCancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryPlayList extends JpaRepository<EntityCancion, Long> {

    EntityCancion findByPosicion(Integer posicion);

    List<EntityCancion> findByOrderByPosicionAsc();

}
