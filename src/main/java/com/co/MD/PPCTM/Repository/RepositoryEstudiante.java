package com.co.MD.PPCTM.Repository;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEstudiante extends JpaRepository<EntityEstudiante, Long> {

    EntityEstudiante findByNumeroNodo(Long numeroNodo);
}
