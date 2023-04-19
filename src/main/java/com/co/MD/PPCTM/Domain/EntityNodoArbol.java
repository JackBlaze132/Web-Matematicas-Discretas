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

   /* @Column(name = "isLeft")
    private Boolean isLeft;

    @Column(name = "isRight")
    private Boolean isRight;*/

    @Column(name = "isFather")
    private Boolean isFather;

    @Column(name = "isSelected")
    private String isSelected;

    public EntityNodoArbol(){

    }

     public EntityNodoArbol(Long pValor, Long pNivelNodo){
         valor = pValor;
         nivelNodo = pNivelNodo;
         hijoIzquierdo = null;
         hijoDerecho = null;
         /*isLeft = Boolean.FALSE;
         isRight = Boolean.FALSE;*/
         isFather = Boolean.FALSE;
         isSelected = "";
     }

}
