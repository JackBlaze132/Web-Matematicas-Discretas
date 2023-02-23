package com.co.MD.PPCTM.Controller;

import com.co.MD.PPCTM.Domain.EntityEstudiante;
import com.co.MD.PPCTM.Services.ServiceEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Frontend {

    @Autowired
    ServiceEstudiante serviceEstudiante;

    @GetMapping (path = "/")
    public String home(){
        return "index";
    }

    @GetMapping (path = "/nodes")
    public String nodos(Model modelo){

        List<EntityEstudiante> listaEstudiantes = serviceEstudiante.listarEstudiantes();
        modelo.addAttribute("estudiantes", listaEstudiantes);

        return "nodes";
    }

    @GetMapping (path = "/error")
    public String error(){
        return "error";
    }

    @GetMapping (path = "/crearEstudiante")
    public String crearEstudiante(Model modelo){

        modelo.addAttribute("nEstudiante", new EntityEstudiante());

        return "crearEstudiante";
    }
}
