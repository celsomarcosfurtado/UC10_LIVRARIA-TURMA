package br.senac.sp.livraria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/editoras")
public class EditoraController {

    @GetMapping
    public String listarEditoras(){
        return "Listando editoras...";
    }

}
