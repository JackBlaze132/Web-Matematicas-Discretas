package com.co.MD.PPCTM.Domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Data
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

    public EntityCancion(String titulo, String autor, String genero, Long numMinutos, Long numSegundos, Integer posicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.numMinutos = numMinutos;
        this.numSegundos = numSegundos;
        this.duracion = "" + numMinutos + ":" + numSegundos;
        this.posicion = posicion;
    }
}
