package com.co.MD.PPCTM.Controller;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import com.co.MD.PPCTM.Repository.RepositoryEstudiante;
import com.co.MD.PPCTM.Services.ServiceEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/estudiante")
public class ControllerEstudiante {

    @Autowired
    ServiceEstudiante serviceEstudiante;

    @Autowired
    RepositoryEstudiante repositoryEstudiante;


    /**
     * Metodo encargado de crear un estudiante al final de la lista enlazada
     * @param estudiante Estudiante el cual será agregado.
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
     *               los atributos que sean agregados.
     * @return La pagina "nodes" en caso de que sea agregado correctamente. La pagina "error" en caso contrario.
     */
    @PostMapping(path = "crearEstudianteAlFinal")
    public RedirectView insertarEstudianteAlFinal(@ModelAttribute EntityEstudiante estudiante, Model modelo){

        Boolean agregado = serviceEstudiante.insertarEstudianteAlFinal(estudiante);

        modelo.addAttribute(estudiante);

        if(agregado.equals(Boolean.TRUE)){
            return new RedirectView("/nodes");
        }
        else{
            return new RedirectView("/error");
        }
    }

    /**
     * Metodo encargado de crear un estudiante al incio de la lista enlazada.
     * @param estudiante Estudiante el cual será agregado.
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
     *               los atributos que sean agregados.
     * @return La pagina "nodes" en caso de que sea agregado correctamente. La pagina "error" en caso contrario.
     */
    @PostMapping(path = "crearEstudianteAlInicio")
    public RedirectView insertarEstudianteAlInicio(@ModelAttribute EntityEstudiante estudiante, Model modelo){

        Boolean agregado = serviceEstudiante.insertarEstudianteAlInicio(estudiante);

        modelo.addAttribute(estudiante);

        if(agregado.equals(Boolean.TRUE)){
            return new RedirectView("/nodes");
        }
        else{
            return new RedirectView("/error");
        }
    }

    /**
     * Metodo encargado de crear un estudiante en x posición.
     * @param estudiante Estudiante el cual será agregado.
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
     *               los atributos que sean agregados.
     * @return La pagina "nodes" en caso de que sea agregado correctamente. La pagina "error" en caso contrario.
     */
    @PostMapping(path = "crearEstudianteXPosicion")
    public RedirectView insertarEstudianteXPosicion(@ModelAttribute EntityEstudiante estudiante, Model modelo){

        Boolean agregado = serviceEstudiante.insertarEstudianteEnXPosicion(estudiante);
        modelo.addAttribute(estudiante);

        if(agregado.equals(Boolean.TRUE)){
            return new RedirectView("/nodes");
        }
        else{
            return new RedirectView("/error");
        }
    }

    /**
     * Metodo encargado de eliminar un estudiante en x posición.
     * @param estudiante Estudiante el cual será eliminado.
     * @param modelo Hace parte del MVC (modelo vista controlador) y sirve para que el html reconozca
     *               los atributos que sean agregados.
     * @return La pagina "nodes" en caso de que sea agregado correctamente. La pagina "error" en caso contrario.
     */
    @DeleteMapping(path = "eliminarEstudianteXPosicion")
    public RedirectView eliminarEstudianteXPosicion(@ModelAttribute EntityEstudiante estudiante, Model modelo){

        try{
            Boolean eliminado = serviceEstudiante.eliminarEnXPosicion(estudiante);

            if(eliminado){
                return new RedirectView("/nodes");
            }
            else{
                return new RedirectView("/error");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new RedirectView("/error");
        }

    }
}
