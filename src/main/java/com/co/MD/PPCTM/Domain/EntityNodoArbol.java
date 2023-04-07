package com.co.MD.PPCTM.Domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "nodoArbol")
@Data
public class EntityNodoArbol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "valor")
    private Long valor;

    @OneToOne
    private EntityNodoArbol hijoIzquierdo;

    @OneToOne
    private EntityNodoArbol hijoDerecho;

    @Column(name = "nivelNodo")
    private Long nivelNodo;


    public EntityNodoArbol(){

    }

     public EntityNodoArbol(Long pValor, Long pNivelNodo){
         valor = pValor;
         nivelNodo = pNivelNodo;
         hijoIzquierdo = null;
         hijoDerecho = null;
     }

}
