package com.co.MD.PPCTM.Repository;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que representa la persistencia de la clase Estudiante haciendo uso
 * de la clase JpaRepository la cual es una interfaz de Java que forma parte
 * del framework Spring Data JPA, y que proporciona una serie de métodos predefinidos
 * para realizar operaciones comunes de persistencia en una base de datos relacional
 * utilizando la tecnología Java Persistence API (JPA).
 */
public interface RepositoryEstudiante extends JpaRepository<EntityEstudiante, Long> {

    //Metodo que encuentra a un estudiante por su numero de nodo.
    EntityEstudiante findByNumeroNodo(Long numeroNodo);

}
