package com.uisrael.consumoopticaperfectvisionapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/examenesvisuales")
public class ExamenVisualController {

    @GetMapping
    public String listar() {
        return "examenesvisuales/listarexamenvisual";
    }
}
