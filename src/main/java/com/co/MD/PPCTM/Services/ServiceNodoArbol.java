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
                    repositoryNodoArbol.save(nodoAGuardar);
                    break;
                }
                else{
                    actual = actual.getHijoDerecho();
                }
            }
        }
        return Boolean.TRUE;
    }

    public void insertarNodoRaiz(){
        repositoryNodoArbol.save(new EntityNodoArbol(50L,1L));
    }


    public List<EntityNodoArbol> listarNodosArbol(){
        return repositoryNodoArbol.findAll();
    }
}
