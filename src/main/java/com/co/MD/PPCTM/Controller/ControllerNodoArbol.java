package com.co.MD.PPCTM.Controller;

import com.co.MD.PPCTM.Domain.EntityNodoArbol;
import com.co.MD.PPCTM.Services.ServiceNodoArbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/arbol")
public class ControllerNodoArbol {

    @Autowired
    ServiceNodoArbol serviceNodoArbol;

    @PostMapping(path = "insertarNodo")
    public RedirectView insertarNodo(@ModelAttribute EntityNodoArbol nodoArbol, Model modelo){

        Boolean agregado = serviceNodoArbol.insertarNodo(nodoArbol.getValor());
        modelo.addAttribute(nodoArbol);

        if(agregado){
            return new RedirectView("/tree");
        }
        return new RedirectView("/screw");
    }

    @PostMapping(path = "insertarNodoRaiz")
    public RedirectView insertarNodoRaiz(@ModelAttribute EntityNodoArbol nodoArbol, Model modelo){
        serviceNodoArbol.insertarNodoRaiz();
        modelo.addAttribute(nodoArbol);
        return new RedirectView("/tree");
    }
}
