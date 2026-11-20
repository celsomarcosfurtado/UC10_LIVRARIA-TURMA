package br.senac.sp.livraria.controller;

import br.senac.sp.livraria.model.Editora;
import br.senac.sp.livraria.repository.EditoraRepository;
import br.senac.sp.livraria.service.EditoraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/editoras")
@Tag(name = "Editoras", description = "API responsável por gerenciar as editoras dos livros. Com essa API será possível inserir, atualizar, listar e excluir as editoras do sistema.")
public class EditoraController {

    private EditoraService editoraService;

    public EditoraController(EditoraService editoraService){
        this.editoraService = editoraService;
    }

    @PostMapping
    public ResponseEntity<Editora> salvar(@RequestBody Editora editora){
        return ResponseEntity
                .status(201)
                .body(editoraService
                        .salvar(editora));
    }


    @GetMapping
    public String listarEditoras(){
        return "Listando editoras...";
    }

}
