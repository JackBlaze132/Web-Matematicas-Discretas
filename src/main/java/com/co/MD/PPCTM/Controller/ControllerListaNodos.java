package com.co.MD.PPCTM.Controller;

import com.co.MD.PPCTM.Domain.EntityListaNodos;
import com.co.MD.PPCTM.Services.ServiceListaNodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lista")
public class ControllerListaNodos {

    @Autowired
    ServiceListaNodos serviceListaNodos;

    @PostMapping(path = "/insertarListaNodosJpa", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarListaNodosJpa(@RequestBody EntityListaNodos listaNodos){

        return new ResponseEntity<Boolean>(serviceListaNodos.insertarListaNodosJpa(listaNodos), HttpStatus.OK);
    }
}
