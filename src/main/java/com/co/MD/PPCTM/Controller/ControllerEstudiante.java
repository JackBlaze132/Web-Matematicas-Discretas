package com.co.MD.PPCTM.Controller;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import com.co.MD.PPCTM.Services.ServiceEstudiante;
import com.co.MD.PPCTM.Services.ServiceListaNodos;
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
    ServiceListaNodos serviceListaNodos;

    @PostMapping(path = "/insertarEstudianteJpa/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarEstudianteJpa(@RequestBody EntityEstudiante estudiante, @PathVariable Long id){

        Boolean condicion = serviceEstudiante.insertarEstudianteJpa(estudiante);

        return new ResponseEntity<Boolean>(condicion, HttpStatus.OK);
    }

    //Nuevos metodos para aplicar al FrontEnd
    //----------------------------------------------------------------------------------------------------------------------------------------

    @PostMapping(path = "insertarEstudiante")
    public RedirectView insertarEstudiante(@ModelAttribute EntityEstudiante estudiante, Model modelo){

        estudiante.setEntityListaNodos(serviceListaNodos.buscarListaNodosPorId(Long.parseLong("2")));
        Boolean agregado = serviceEstudiante.insertarEstudianteJpa(estudiante);

        modelo.addAttribute(estudiante);

        if(agregado.equals(Boolean.TRUE)){
            return new RedirectView("/nodes");
        }
        else{
            return new RedirectView("/error");
        }
    }
}
