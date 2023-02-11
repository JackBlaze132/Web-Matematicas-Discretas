package com.co.MD.PPCTM.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Frontend {

    @GetMapping (path = "/")
    public String home(){
        return "index";
    }
}
