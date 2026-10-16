package com.greendogdelivery.springboot_greendogdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AmbienteController {
    
    @GetMapping("/ambiente")
    public String ambiente() {
        return "ambiente";
    }
    
}
