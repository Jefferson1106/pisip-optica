package com.uisrael.consumoopticaperfectvisionapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ordenes-entrega")
public class OrdenEntregaController {

    @GetMapping
    public String listar() {
        return "/ordenesentrega/listarordenentrega";
    }
}
