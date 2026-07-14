package com.uisrael.consumoopticaperfectvisionapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/detalle-catalogo")
public class DetalleCatalogoController {

    @GetMapping
    public String listar() {
        return "/detallecatalogo/listardetallecatalogo";
    }
}
