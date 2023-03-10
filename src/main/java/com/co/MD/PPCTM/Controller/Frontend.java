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
        modelo.addAttribute("numeroNodos", listaEstudiantes.size());
        System.out.println("Hola, entré");

        return "nodes";
    }

    @GetMapping (path = "/error")
    public String error(Model modelo){
        modelo.addAttribute("tipoError", "No puede eliminar un nodo inexistente");
        System.out.println(modelo.getAttribute("tipoError"));
        return "error";
    }

    @GetMapping (path = "/crearEstudianteAlFinal")
    public String crearEstudianteAlFinal(Model modelo){

        modelo.addAttribute("nEstudiante", new EntityEstudiante());

        return "crearEstudianteAlFinal";
    }

    @GetMapping (path = "/crearEstudianteAlInicio")
    public String crearEstudianteAlInicio(Model modelo){

        modelo.addAttribute("nEstudiante", new EntityEstudiante());

        return "crearEstudianteAlInicio";
    }

    @GetMapping (path = "/crearEstudianteXPosicion")
    public String crearEstudianteXPosicion(Model modelo){

        modelo.addAttribute("nEstudiante", new EntityEstudiante());

        return "crearEstudianteXPosicion";
    }

    @GetMapping (path = "/eliminarEnXposicion")
    public String eliminarEstudianteEnXPosicion(Model modelo){

        modelo.addAttribute("nEstudiante", new EntityEstudiante());

        return "eliminarEnXPosicion";
    }
}
