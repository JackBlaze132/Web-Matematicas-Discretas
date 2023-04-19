package com.co.MD.PPCTM.Repository;

import com.co.MD.PPCTM.Domain.EntityNodoArbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryNodoArbol extends JpaRepository<EntityNodoArbol, Long> {

    EntityNodoArbol findByValor(Long valor);

    EntityNodoArbol findByHijoIzquierdo(EntityNodoArbol nodoArbol);

    EntityNodoArbol findByHijoDerecho(EntityNodoArbol nodoArbol);
}
