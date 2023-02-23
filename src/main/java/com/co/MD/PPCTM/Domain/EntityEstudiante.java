package com.co.MD.PPCTM.Domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "estudiante")
@Data
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

    @JoinColumn(name = "id_listanodos", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EntityListaNodos entityListaNodos;


}
