package com.co.MD.PPCTM.Controller;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import com.co.MD.PPCTM.Repository.RepositoryEstudiante;
import com.co.MD.PPCTM.Services.ServiceEstudiante;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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


    @PostMapping(path = "/insertarEstudianteJpa/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarEstudianteJpa(@RequestBody EntityEstudiante estudiante, @PathVariable Long id){

        Boolean condicion = serviceEstudiante.insertarEstudianteAlFinal(estudiante);

        return new ResponseEntity<Boolean>(condicion, HttpStatus.OK);
    }

    //Nuevos metodos para aplicar al FrontEnd
    //----------------------------------------------------------------------------------------------------------------------------------------

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

    @DeleteMapping(path = "eliminarEstudianteXPosicion")
    public RedirectView eliminarEstudianteXPosicion(@ModelAttribute EntityEstudiante estudiante, Model modelo){

        try{
            Boolean elimiando = serviceEstudiante.eliminarEnXPosicion(estudiante);
        }
        catch(Exception e){
            e.printStackTrace();
            return new RedirectView("/error");
        }

        return new RedirectView("/nodes");
    }
}
