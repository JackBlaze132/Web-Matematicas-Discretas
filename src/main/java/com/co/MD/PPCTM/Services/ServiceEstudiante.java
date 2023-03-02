package com.co.MD.PPCTM.Services;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import com.co.MD.PPCTM.Repository.RepositoryEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEstudiante {

    @Autowired
    RepositoryEstudiante repositoryEstudiante;

    /**
     *Este metodo sirve para que al momento de agregar a un nuevo estudiante al final de la lista
     * se asignen los valores correspondientes a siguiente y anterior del nodo "adelante" y
     * asignar el atributo siguiente del nodo "atras"
     * @param atras Estudiante que estará detras del nodo recien agregado
     * @param adelante Estudiante que será recien agregado como ultimo nodo
     */
    public void agregarEstudianteF(EntityEstudiante atras, EntityEstudiante adelante){

        atras.setSiguiente(adelante);
        adelante.setSiguiente(null);
        adelante.setAnterior(atras);
    }

    /**
     *Este metodo sirve para agregar un nuevo nodo al inicio de la lista, asigna los valores de
     * "anterior" y "siguiente" al nodo que quedará al principio de la lista y actualizará los demás nodos.
     * @param cabeceraActual Nodo que pasará a ocupar la segunda posición.
     * @param nuevaCabecera Nodo que ocupará la primera posición.
     */
    public void agregarEstudianteI(EntityEstudiante cabeceraActual, EntityEstudiante nuevaCabecera){

        nuevaCabecera.setAnterior(null);
        nuevaCabecera.setSiguiente(cabeceraActual);
        nuevaCabecera.setNumeroNodo(1L);

        cabeceraActual.setAnterior(nuevaCabecera);
        cabeceraActual.setNumeroNodo(2L);

        EntityEstudiante temp = cabeceraActual;

        Long contador = 3L;

        while (temp.getSiguiente() != null){

            temp = temp.getSiguiente();
            temp.setNumeroNodo(contador);
            contador++;
        }
    }

    public Boolean insertarEstudianteAlFinal(EntityEstudiante estudiante){

        Long numeroNodos = 1L;

        try{

            if(repositoryEstudiante.findAll().size() == 0){
                estudiante.setAnterior(null);
                estudiante.setSiguiente(null);
                estudiante.setNumeroNodo(numeroNodos);
            }
            else{

                EntityEstudiante actual = repositoryEstudiante.findByNumeroNodo(numeroNodos);
                numeroNodos = 2L;

                while(actual.getSiguiente() != null){

                    numeroNodos ++;
                    actual = actual.getSiguiente();
                }
                estudiante.setNumeroNodo(numeroNodos);
                agregarEstudianteF(actual, estudiante);
            }

            repositoryEstudiante.save(estudiante);
        } catch (Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean insertarEstudianteAlInicio(EntityEstudiante estudiante){

        if(repositoryEstudiante.findAll().size() == 0){
            insertarEstudianteAlFinal(estudiante);
        }
        else{
            try{
                agregarEstudianteI(repositoryEstudiante.findByNumeroNodo(1L), estudiante);
                repositoryEstudiante.save(estudiante);
            }
            catch (Exception e){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public Boolean insertarEstudianteEnXPosicion(EntityEstudiante estudiante){

        int aIngresar = estudiante.getNumeroNodo().intValue();
        int numEstudiantes = repositoryEstudiante.findAll().size();

        if(aIngresar < 1){
            return Boolean.FALSE;
        }
        else if( aIngresar > numEstudiantes ){
            insertarEstudianteAlFinal(estudiante);

        }
        else if(aIngresar == numEstudiantes){

            if(numEstudiantes == 1){
                insertarEstudianteAlInicio(estudiante);
            }
            else{
                EntityEstudiante actual = repositoryEstudiante.findByNumeroNodo(1L);
                while(actual != null){
                    if(actual.getSiguiente() == null){
                        break;
                    }
                    actual = actual.getSiguiente();
                }

                actual.getAnterior().setSiguiente(estudiante);
                estudiante.setAnterior(actual.getAnterior());
                estudiante.setSiguiente(actual);
                actual.setAnterior(estudiante);
                actual.setNumeroNodo(estudiante.getNumeroNodo() + 1L);
            }
        }
        repositoryEstudiante.save(estudiante);
        return Boolean.TRUE;
    }




    public List<EntityEstudiante> listarEstudiantes(){

        return repositoryEstudiante.findAll();
    }
}
