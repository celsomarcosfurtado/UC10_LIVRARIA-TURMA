package br.senac.sp.livraria.controller;

import br.senac.sp.livraria.model.Livro;
import br.senac.sp.livraria.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// POST http://localhost:8080/v1/api/livros
// GET  http://localhost:8080/v1/api/livros

@RestController
@RequestMapping("/v1/api/livros")
public class LivroController {

    private LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> salvar(@RequestBody Livro livro){
        Livro novoLivro = livroService.salvar(livro);
        return ResponseEntity.status(201).body(novoLivro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros(){
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable("codigo") int id){
        Optional<Livro> livroEncontrado = livroService.buscarPorId(id);

        if (livroEncontrado.isPresent()){
            return ResponseEntity.ok(livroEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
