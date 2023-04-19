package com.co.MD.PPCTM.Services;

import com.co.MD.PPCTM.Domain.EntityNodoArbol;
import com.co.MD.PPCTM.Repository.RepositoryNodoArbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                    //nodoAGuardar.setIsLeft(Boolean.TRUE);
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
                    //nodoAGuardar.setIsRight(Boolean.TRUE);
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

    public Boolean buscarNodo(Long valor){
        EntityNodoArbol buscado = repositoryNodoArbol.findByValor(valor);
        List<EntityNodoArbol> listaNodos = listarNodosArbol();
        if(buscado == null){
            return Boolean.FALSE;
        }
        for (EntityNodoArbol nodoArbol: listaNodos) {
            nodoArbol.setIsSelected("");
        }
        buscado.setIsSelected("isSelected");
        repositoryNodoArbol.save(buscado);
        return Boolean.TRUE;
    }

    @Transactional
    public Boolean eliminarNodo(EntityNodoArbol nodoArbol){
        EntityNodoArbol padreNodoEliminar = repositoryNodoArbol.findByHijoIzquierdo(repositoryNodoArbol.findByValor(nodoArbol.getValor()));
        EntityNodoArbol buscado = repositoryNodoArbol.findByValor(nodoArbol.getValor());
        EntityNodoArbol aux = new EntityNodoArbol();
        if(buscado.getIsFather()){
            return Boolean.FALSE;
        }
        if(padreNodoEliminar == null){
            padreNodoEliminar = repositoryNodoArbol.findByHijoDerecho(repositoryNodoArbol.findByValor(nodoArbol.getValor()));
            aux = padreNodoEliminar.getHijoDerecho();
            padreNodoEliminar.setHijoDerecho(null);
            repositoryNodoArbol.save(padreNodoEliminar);
            repositoryNodoArbol.deleteById(aux.getId());
        }
        else{
            aux = padreNodoEliminar.getHijoIzquierdo();
            padreNodoEliminar.setHijoIzquierdo(null);
            repositoryNodoArbol.save(padreNodoEliminar);
            repositoryNodoArbol.deleteById(aux.getId());
        }
        return Boolean.TRUE;
    }

    public String recorrerPreOrden(EntityNodoArbol actual, String cadena) {
        if (actual == null) {
            return cadena;
        }

        cadena = cadena + actual.getValor() + "-";

        cadena = recorrerPreOrden(actual.getHijoIzquierdo(), cadena);

        cadena = recorrerPreOrden(actual.getHijoDerecho(), cadena);

        return cadena;
    }

    public String recorrerInOrden(EntityNodoArbol actual, String cadena){
        if (actual == null){
            return cadena;
        }

        cadena = recorrerInOrden(actual.getHijoIzquierdo(), cadena);

        cadena = cadena + actual.getValor() + "-";

        cadena = recorrerInOrden(actual.getHijoDerecho(), cadena);

        return cadena;
    }

    public String recorrerPostOrden(EntityNodoArbol actual, String cadena){
        if (actual == null){
            return cadena;
        }

        cadena = recorrerPostOrden(actual.getHijoIzquierdo(), cadena);

        cadena = recorrerPostOrden(actual.getHijoDerecho(), cadena);

        cadena = cadena + actual.getValor() + "-";

        return cadena;
    }



    public List<EntityNodoArbol> listarNodosArbol(){
        return repositoryNodoArbol.findAll();
    }
}
