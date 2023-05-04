package com.co.MD.PPCTM.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class EntityCancion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    private String autor;

    private String genero;

    @Transient
    private Long numMinutos;

    @Transient
    private Long numSegundos;

    private String duracion;

    private Integer posicion;

    public EntityCancion(){

    }

    public EntityCancion(String titulo, String autor, String genero, Long numMinutos, Long numSegundos, String duracion,Integer posicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.numMinutos = numMinutos;
        this.numSegundos = numSegundos;
        this.duracion = duracion;
        this.posicion = posicion;
    }
}
