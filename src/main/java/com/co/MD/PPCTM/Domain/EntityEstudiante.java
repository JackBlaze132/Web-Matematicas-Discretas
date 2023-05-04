package com.co.MD.PPCTM.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
public class EntityEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "numeroNodo")
    private Long numeroNodo;

    @OneToOne
    private EntityEstudiante siguiente;

    @OneToOne
    private EntityEstudiante anterior;


}
