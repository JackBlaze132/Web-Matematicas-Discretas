package com.co.MD.PPCTM.Services;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import com.co.MD.PPCTM.Repository.RepositoryEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

        // El enlace anterior de la nueva cabecera se le asigna el valor de null, ya que no tiene ningún nodo anterior.
        nuevaCabecera.setAnterior(null);
        // Se establece el enlace siguiente de la nueva cabecera al nodo actual, ya que será el siguiente nodo en la lista.
        nuevaCabecera.setSiguiente(cabeceraActual);
        // Se establece el número de nodo de la nueva cabecera como 1.
        nuevaCabecera.setNumeroNodo(1L);
        // Se establece el enlace anterior del nodo actual al nuevo nodo, ya que será su nodo anterior.
        cabeceraActual.setAnterior(nuevaCabecera);
        // Se establece el número de nodo del nodo actual como 2.
        cabeceraActual.setNumeroNodo(2L);
        // Se crea una variable temporal que toma como valor la cabecera actual o el nodo número 2.
        EntityEstudiante temp = cabeceraActual;

        // Establecer el contador inicial en 3, ya que los dos primeros nodos son la cabecera actual y la nueva cabecera.
        Long contador = 3L;

        while (temp.getSiguiente() != null){

            temp = temp.getSiguiente();
            temp.setNumeroNodo(contador);

            contador++;
        }
    }

    /**
     * Metodo encargado de la logica de insertar un estudiante al final de una lista doblemente enlazada.
     * @param estudiante Estudiante el cual será agregado.
     * @return true en caso de que pueda ser agregado, false de lo contrario.
     */
    public Boolean insertarEstudianteAlFinal(EntityEstudiante estudiante){

        // Establecer el número de nodos en 1.
        Long numeroNodos = 1L;

        try{
            // Si la lista está vacía, establecer el nodo como el único nodo en la lista.
            if(repositoryEstudiante.findAll().size() == 0){
                estudiante.setAnterior(null);
                estudiante.setSiguiente(null);
                estudiante.setNumeroNodo(numeroNodos);
            }
            else{
                // Si la lista no está vacía, se buscará el último nodo de la lista.

                // Al comprobar que la lista no está vaciá, nos aseguramos de que el valor de actual no sea null.
                EntityEstudiante actual = repositoryEstudiante.findByNumeroNodo(numeroNodos);

                // Se incrementa el número de nodos a 1 para comenzar la búsqueda desde el primer nodo.
                numeroNodos = 1L;

                // Se recorre la lista hasta el ultimo nodo.
                while(actual.getSiguiente() != null){

                    numeroNodos ++;
                    actual = actual.getSiguiente();
                }

                // Se establece el número de nodo del nuevo estudiante como el número del último nodo en la lista +1.
                estudiante.setNumeroNodo(numeroNodos + 1);

                // Se agrega el nuevo estudiante al final de la lista.
                agregarEstudianteF(actual, estudiante);
            }

            // Se guarda el nuevo estudiante en la base de datos.
            repositoryEstudiante.save(estudiante);
        } catch (Exception e){
            // Si ocurre una excepción, retorna falso.
            return Boolean.FALSE;
        }

        // Si no hay problemas, retorna verdadero.
        return Boolean.TRUE;
    }

    /**
     * Metodo encargado de la logica de insertar un estudiante al inicio de una lista doblemente enlazada.
     * @param estudiante Estudiante el cual será agregad al incio de la lista doblemente enlazada.
     * @return true en caso de que pueda ser agregado, false de lo contrario.
     */
    public Boolean insertarEstudianteAlInicio(EntityEstudiante estudiante){

        // Si la lista se encuentra vacía crea un estudiante (el final y el inicio de la lista sería el mismo).
        if(repositoryEstudiante.findAll().size() == 0){
            insertarEstudianteAlFinal(estudiante);
        }
        else{
            try{
                // Se intenta agregar un nuevo estudiante al principio de la lista haciendo uso del metodo "agregarEstudianteI".
                agregarEstudianteI(repositoryEstudiante.findByNumeroNodo(1L), estudiante);
                repositoryEstudiante.save(estudiante);
            }
            catch (Exception e){
                // Si ocurre una excepción, retorna falso.
                return Boolean.FALSE;
            }
        }
        // Si no hay problemas, retorna verdadero.
        return Boolean.TRUE;
    }

    /**
     * Metodo encargado de insertar un estudiante en una posición x de la lista doblemente enlazada.
     * @param estudiante Estudiante el cual será agregado.
     * @return true en caso de que el estudiante pueda ser agregado, false de lo contrario.
     */
    public Boolean insertarEstudianteEnXPosicion(EntityEstudiante estudiante) {
        // Obtenemos el número de nodo del estudiante que se desea insertar.
        int aIngresar = estudiante.getNumeroNodo().intValue();
        // Obtenemos el número de estudiantes en la lista.
        int numEstudiantes = repositoryEstudiante.findAll().size();

        // Si la posición en la que se desea insertar es inválida, se devuelve falso.
        if (aIngresar < 1) {
            return Boolean.FALSE;
        } else if (aIngresar > numEstudiantes) {
            /*
            Si la posición deseada es mayor que el número de estudiantes,
            se inserta el estudiante al final de la lista.
             */
            insertarEstudianteAlFinal(estudiante);
        } else if (aIngresar == 1) {
            /*
            Si la posición deseada es la primera posición, se inserta
            el estudiante al inicio de la lista.
             */
            insertarEstudianteAlInicio(estudiante);
        } else if (aIngresar == numEstudiantes) {
            /*
            Si la posición deseada es la última posición, se busca el último
            elemento de la lista y se ajustan los punteros para insertar el nuevo estudiante
             */
            EntityEstudiante actual = repositoryEstudiante.findByNumeroNodo(1L);
            while (actual != null) {
                if (actual.getSiguiente() == null) {
                    break;
                }
                actual = actual.getSiguiente();
            }
            actual.getAnterior().setSiguiente(estudiante);
            estudiante.setAnterior(actual.getAnterior());
            estudiante.setSiguiente(actual);
            actual.setAnterior(estudiante);
            actual.setNumeroNodo(estudiante.getNumeroNodo() + 1L);
        } else {
            /*
            Si la posición deseada está en medio de la lista, se busca el
            elemento anterior a la posición deseada y se ajustan los punteros
            para insertar el nuevo estudiante
             */
            EntityEstudiante actual = repositoryEstudiante.findByNumeroNodo(1L);
            int contador = 1;
            int numeroALllegar = estudiante.getNumeroNodo().intValue();

            while (contador != numeroALllegar) {
                contador++;
                actual = actual.getSiguiente();
            }

            actual.getAnterior().setSiguiente(estudiante);
            estudiante.setAnterior(actual.getAnterior());
            estudiante.setSiguiente(actual);
            actual.setAnterior(estudiante);

            /*
            Después de insertar el nuevo estudiante, se actualizan los
            números de nodo de los demás estudiantes en la lista
             */
            while (actual != null) {
                actual.setNumeroNodo(actual.getNumeroNodo() + 1L);
                actual = actual.getSiguiente();
            }
        }
        // Se guarda el estudiante en el repositorio y se devuelve verdadero
        repositoryEstudiante.save(estudiante);
        return Boolean.TRUE;
    }


    /**
     * Método que verifica si existe un estudiante en una posición específica en una lista enlazada y
     * devuelve un HashMap con información relevante sobre la posición dada.
     * @param primerEstudiante El primer estudiante de la lista enlazada.
     * @param posicionAEliminar La posición del estudiante a verificar.
     * @return Un HashMap con una clave booleana que indica si se encontró o no un estudiante en la posición dada
     * y un valor entero que indica si el nodo siguiente a la posición dada es nulo o no.
     */
    public HashMap<Boolean, Integer> verificarHayUnEstudianteXPosicion(EntityEstudiante primerEstudiante, Long posicionAEliminar){

        // HashMap para almacenar los resultados
        HashMap<Boolean, Integer> devolver = new HashMap<>();

        // Variables de contador para iterar a través de la lista y almacenar información relevante
        int contador = 1;
        int auxiliar = 0;

        // Se convierte la posición dada a un entero y se crea una variable temporal para el primer estudiante de la lista
        int posicion = posicionAEliminar.intValue();
        EntityEstudiante temp = primerEstudiante;

        if(primerEstudiante != null && posicion == 1){
            devolver.put(Boolean.TRUE,2);
            return devolver;
        }
        // Si la lista está vacía, no hay estudiantes en la posición dada, por lo que se devuelve un HashMap con FALSE y 0
        if(temp == null){
            devolver.put(Boolean.FALSE, 0);
            return devolver;
        }

        while(temp.getSiguiente() != null){
            contador++;
            temp = temp.getSiguiente();

            // Si el contador es igual a la posición dada, se ha encontrado un estudiante en la posición dada
            if(contador == posicion){
                auxiliar = 1;
                break;
            }
        }

        // Si no se ha encontrado un estudiante en la posición dada, se devuelve un HashMap con FALSE y 0
        if(auxiliar == 0){
            devolver.put(Boolean.FALSE, 0);
        }
        // Si se ha encontrado un estudiante en la posición dada y el siguiente nodo no es nulo, se devuelve un HashMap con TRUE y 1
        else if(temp.getSiguiente() != null){
            devolver.put(Boolean.TRUE, 1);
        }
        // Si se ha encontrado un estudiante en la posición dada y el siguiente nodo es nulo, se devuelve un HashMap con TRUE y 0
        else{
            devolver.put(Boolean.TRUE, 0);
        }

        return devolver;
    }



    /**
     * Método que elimina un estudiante en una posición determinada en la lista doblemente enlazada.
     * @param estudiante Objeto de tipo EntityEstudiante a eliminar
     * @return true si el estudiante fue eliminado exitosamente, false en caso contrario
     */
    public Boolean eliminarEnXPosicion(EntityEstudiante estudiante){

        // Se obtiene el número total de estudiantes en la lista
        int numEstudiantes = listarEstudiantes().size();

        // Se obtiene la posición del estudiante a eliminar
        int posicionAEliminar = estudiante.getNumeroNodo().intValue();

        // Se busca el primer estudiante en la lista
        EntityEstudiante buscado = repositoryEstudiante.findByNumeroNodo(1L);

        // Se verifica si la posición del estudiante a eliminar existe en la lista
        HashMap<Boolean, Integer> casos = verificarHayUnEstudianteXPosicion(buscado, estudiante.getNumeroNodo());
        Boolean condicion = casos.containsKey(Boolean.TRUE);
        Integer numCaso = casos.get(condicion);

        // Si la variable condición es falsa, no existe un estudiante en esa posición, por lo que se retorna false.
        if(!condicion){
            return Boolean.FALSE;
        }

        // Se recorre la lista hasta encontrar el estudiante a eliminar
        int contador = 1;
        while(buscado.getSiguiente() != null){

            contador++;
            buscado = buscado.getSiguiente();
            if(contador == posicionAEliminar){
                break;
            }
        }

        // Se eliminan los nodos en los distintos casos posibles
        if( (numEstudiantes == 1) && (posicionAEliminar == 1) ){
            // Caso especial: un solo nodo en la lista
            repositoryEstudiante.deleteById(buscado.getId());
            return Boolean.TRUE;
        }
        else if(posicionAEliminar == 1){
            // Caso especial: eliminar el primer nodo
            buscado = repositoryEstudiante.findByNumeroNodo(1L);
            buscado.getSiguiente().setAnterior(null);
            Long id = buscado.getId();

            EntityEstudiante aux = buscado.getSiguiente();
            while(aux != null){

                aux.setNumeroNodo(aux.getNumeroNodo() - 1L);
                if(aux.getSiguiente() == null){
                    break;
                }
                aux = aux.getSiguiente();
            }
            // Se borra el estudiante por id.
            repositoryEstudiante.deleteById(id);
            return Boolean.TRUE;
        }
        else if( (condicion) && (numCaso == 0) ){
            // Caso especial: eliminar el último nodo
            buscado.getAnterior().setSiguiente(null);
            buscado.setAnterior(null);
            buscado.setSiguiente(null);
            repositoryEstudiante.deleteById(buscado.getId());
            return Boolean.TRUE;
        }
        else if( (condicion) && (numCaso == 1) ){
            // Caso general: eliminar un nodo en medio de la lista
            buscado.getAnterior().setSiguiente(buscado.getSiguiente());
            buscado.getSiguiente().setAnterior(buscado.getAnterior());

            EntityEstudiante aux = repositoryEstudiante.findByNumeroNodo(buscado.getNumeroNodo());
            while(buscado.getSiguiente() != null){

                buscado = buscado.getSiguiente();
                buscado.setNumeroNodo(buscado.getNumeroNodo() - 1L);
            }
            aux.setAnterior(null);
            aux.setSiguiente(null);
            repositoryEstudiante.deleteById(aux.getId());
            return Boolean.TRUE;
        }
        /*
        En caso de que no se cumpla alguno de los casos anteriores no se habrá
        eliminado al estudiante, por lo que se retorna false.
         */
        return Boolean.FALSE;
    }


    /**
     * Metodo encargado de listar todos los estudiantes de la lista doblemente enlazada.
     * @return La lista de los estudiantes.
     */
    public List<EntityEstudiante> listarEstudiantes(){

        return repositoryEstudiante.findAll();
    }

}
