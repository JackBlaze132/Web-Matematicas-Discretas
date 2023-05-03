package com.co.MD.PPCTM.Controller;

import com.co.MD.PPCTM.Domain.EntityCancion;
import com.co.MD.PPCTM.Repository.RepositoryPlayList;
import com.co.MD.PPCTM.Services.ServicePlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping(value = "/playList")
public class ControllerPlayList {

    @Autowired
    ServicePlayList servicePlayList;

    @Autowired
    RepositoryPlayList repositoryPlayList;

    @PostMapping(value = "/crearCancion")
    public RedirectView insertarCancion(@ModelAttribute EntityCancion cancion){

        Boolean agregado = servicePlayList.agregarCancion(cancion);

        if(agregado){
            return new RedirectView("/playList");
        }

        return new RedirectView("/screw");
    }

    @DeleteMapping(value = "/eliminarCancion")
    public RedirectView eliminarCancion(@ModelAttribute EntityCancion cancion){

        Boolean eliminado = servicePlayList.eliminarCancion(cancion);

        if(eliminado){
            return new RedirectView("/playList");
        }
        return new RedirectView("/screw");
    }

    @PostMapping(value = "/editarCancion")
    public RedirectView editarCancion(@ModelAttribute EntityCancion cancion){

        Boolean editado = servicePlayList.editarCancion(cancion);

        if(editado){
            return new RedirectView("/playList");
        }
        return new RedirectView("/screw");
    }

    @PostMapping(value = "/subirCancion")
    public RedirectView subirCancion(@RequestParam("id") Long id){
        EntityCancion cancion = repositoryPlayList.findById(id).get();
        if (cancion == null) {
            return new RedirectView("/screw");
        }

        Boolean editado = servicePlayList.subirCancion(cancion);

        if(editado){
            return new RedirectView("/playList");
        }
        return new RedirectView("/screw");
    }

    @PostMapping(value = "/bajarCancion")
    public RedirectView bajarCancion(@RequestParam("id") Long id){
        EntityCancion cancion = repositoryPlayList.findById(id).get();

        Boolean editado = servicePlayList.bajarCancion(cancion);

        if(editado){
            return new RedirectView("/playList");
        }
        return new RedirectView("/screw");
    }

    @GetMapping(value = "/cancion/{id}")
    @ResponseBody
    public EntityCancion getCancion(@PathVariable Long id){
        Optional<EntityCancion> cancion = repositoryPlayList.findById(id);
        return cancion.orElse(null);
    }

}
