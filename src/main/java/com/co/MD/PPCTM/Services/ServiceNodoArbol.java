package com.co.MD.PPCTM.Services;

import com.co.MD.PPCTM.Domain.EntityNodoArbol;
import com.co.MD.PPCTM.Repository.RepositoryNodoArbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceNodoArbol {

    @Autowired
    RepositoryNodoArbol repositoryNodoArbol;

    private EntityNodoArbol raiz;

    public Boolean insertarNodo(Long valor){

        raiz = repositoryNodoArbol.findByValor(50L);
        EntityNodoArbol actual = raiz;
        Long contador = 1L;

        while(true){
            contador++;
            if(valor.equals(actual.getValor())){
                return Boolean.FALSE;
            }
            if(valor < actual.getValor()){

                if(actual.getHijoIzquierdo() == null){
                    EntityNodoArbol nodoAGuardar = new EntityNodoArbol(valor, contador);
                    actual.setHijoIzquierdo(nodoAGuardar);
                    nodoAGuardar.setIsLeft(Boolean.TRUE);
                    repositoryNodoArbol.save(nodoAGuardar);
                    break;
                }
                else{
                    actual = actual.getHijoIzquierdo();
                }
            }
            else if(valor > actual.getValor()){

                if(actual.getHijoDerecho() == null){
                    EntityNodoArbol nodoAGuardar = new EntityNodoArbol(valor, contador);
                    actual.setHijoDerecho(nodoAGuardar);
                    nodoAGuardar.setIsRight(Boolean.TRUE);
                    repositoryNodoArbol.save(nodoAGuardar);
                    break;
                }
                else{
                    actual = actual.getHijoDerecho();
                }
            }
        }
        List<EntityNodoArbol> listaNodos = listarNodosArbol();
        for (EntityNodoArbol nodo: listaNodos) {
            if( ((nodo.getHijoIzquierdo() != null) || (nodo.getHijoDerecho() != null)) ){
                nodo.setIsFather(Boolean.TRUE);
                repositoryNodoArbol.save(nodo);
            }
        }
        return Boolean.TRUE;
    }

    public void insertarNodoRaiz(){
        EntityNodoArbol raiz = new EntityNodoArbol(50L,1L);
        raiz.setIsFather(Boolean.TRUE);
        repositoryNodoArbol.save(raiz);
    }

    public List<EntityNodoArbol> listarNodosArbol(){
        return repositoryNodoArbol.findAll();
    }
}
