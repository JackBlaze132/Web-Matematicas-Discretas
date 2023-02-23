package com.co.MD.PPCTM.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "listanodos")
@Data
public class EntityListaNodos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityListaNodos")
    @JsonIgnore
    private Collection<EntityEstudiante> estudianteCollection;
}
