package com.co.MD.PPCTM.Controller;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import com.co.MD.PPCTM.Domain.EntityNodoArbol;
import com.co.MD.PPCTM.Repository.RepositoryNodoArbol;
import com.co.MD.PPCTM.Services.ServiceEstudiante;
import com.co.MD.PPCTM.Services.ServiceNodoArbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Clase que realiza el trabajo de enrutar las direcciones en la web.
 */
@Controller
public class Frontend {

    @Autowired
    ServiceEstudiante serviceEstudiante;

    @Autowired
    ServiceNodoArbol serviceNodoArbol;

    @Autowired
    RepositoryNodoArbol repositoryNodoArbol;

    /**
     * Método que redirige a la página de inicio
     * @return página de inicio de la aplicación
     */
    @GetMapping (path = "/")
    public String home(){
        return "index";
    }

    /**
     * Método que crea un nuevo modelo que permite mostrar la lista de nodos y su tamaño
     * Crea una lista con los datos de los estudiantes
     * Asigna un nuevo atributo al modelo que permite llamar la lista de estudiantes
     * Asigna un nuevo atributo al modelo que permite conocer el tamaño de la lista de estudiantes
     * Se retorna la página "/nodes" con los cambios realizados
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
                     los atributos que sean agregados
     * @return página "nodes"
     */
    @GetMapping (path = "/nodes")
    public String nodos(Model modelo){

        List<EntityEstudiante> listaEstudiantes = serviceEstudiante.listarEstudiantes();
        modelo.addAttribute("estudiantes", listaEstudiantes);
        modelo.addAttribute("numeroNodos", listaEstudiantes.size());

        return "nodes";
    }

    /**
     * Método que crea un nuevo modelo que permite mostrar un mensaje de error en la página "/screw"
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
     *               los atributos que sean agregados
     * @return página de error
     */
    @GetMapping (path = "/screw")
    public String screw(Model modelo){
        modelo.addAttribute("tipoError", "Error interno del servidor");

        return "screw";
    }

    @GetMapping(path = "/tree")
    public String tree(Model modelo){
        List<EntityNodoArbol> listaNodos = serviceNodoArbol.listarNodosArbol();

        modelo.addAttribute("listaNodos",listaNodos);
        modelo.addAttribute("numeroNodos",listaNodos.size());
        modelo.addAttribute("preOrden",serviceNodoArbol.recorrerPreOrden(repositoryNodoArbol.findByValor(50L),""));
        modelo.addAttribute("postOrden",serviceNodoArbol.recorrerPostOrden(repositoryNodoArbol.findByValor(50L),""));
        modelo.addAttribute("inOrden",serviceNodoArbol.recorrerInOrden(repositoryNodoArbol.findByValor(50L),""));

        return "tree";
    }

    @GetMapping (path = "/crearNodo")
    public String crearNodo(Model modelo){

        modelo.addAttribute("nNodo", new EntityNodoArbol());

        return "crearNodo";
    }

    @GetMapping (path = "/buscarNodo")
    public String buscarNodo(Model modelo){

        modelo.addAttribute("nNodo", new EntityNodoArbol());

        return "buscarNodo";
    }

    @GetMapping (path = "/eliminarNodo")
    public String eliminarNodo(Model modelo){

        modelo.addAttribute("nNodo", new EntityNodoArbol());

        return "eliminarNodo";
    }

    /**
     * Método que crea un nuevo modelo que permite crea un nodo al final de la lista
     * Asigna un nuevo atributo al modelo, que crea un nuevo nodo
     * Se redirige a la página de "/crearEstudianteAlFinal"
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
     *               los atributos que sean agregados
     * @return página "crearEstudiantesAlFinal"
     */
    @GetMapping (path = "/crearEstudianteAlFinal")
    public String crearEstudianteAlFinal(Model modelo){

        modelo.addAttribute("nEstudiante", new EntityEstudiante());

        return "crearEstudianteAlFinal";
    }

    /**
     * Método que crea un nuevo modelo que permite crea un nodo al inicio de la lista
     * Asigna un nuevo atributo al modelo, que crea un nuevo nodo
     * Se redirige a la página de "/crearEstudianteAlInicio"
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
     *               los atributos que sean agregados
     * @return página "crearEstudianteAlInicio"
     */
    @GetMapping (path = "/crearEstudianteAlInicio")
    public String crearEstudianteAlInicio(Model modelo){

        modelo.addAttribute("nEstudiante", new EntityEstudiante());

        return "crearEstudianteAlInicio";
    }

    /**
     * Método que crea un nuevo modelo que permite crea un nodo al final de la lista
     * Asigna un nuevo atributo al modelo, que crea un nuevo nodo
     * Se redirige a la página de "/crearEstudianteAlFinal"
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
     *               los atributos que sean agregados
     * @return página "crearEstudianteXPosicion"
     */
    @GetMapping (path = "/crearEstudianteXPosicion")
    public String crearEstudianteXPosicion(Model modelo){

        modelo.addAttribute("nEstudiante", new EntityEstudiante());

        return "crearEstudianteXPosicion";
    }

    /**
     * Método crea un nuevo modelo que elimina un nodo dada una posición
     * Asigna un nuevo atributo al modelo, que recibe un nodo
     * Redirige a la página de "/eliminarEnXPosicion"
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
     *               los atributos que sean agregados
     * @return página "eliminarEnXPosicion"
     */
    @GetMapping (path = "/eliminarEnXposicion")
    public String eliminarEstudianteEnXPosicion(Model modelo){

        modelo.addAttribute("nEstudiante", new EntityEstudiante());

        return "eliminarEnXPosicion";
    }
}
