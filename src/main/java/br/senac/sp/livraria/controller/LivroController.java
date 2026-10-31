package br.senac.sp.livraria.controller;

import br.senac.sp.livraria.model.Livro;
import br.senac.sp.livraria.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        System.out.println("Endpoint chamado...");
        Livro novoLivro = livroService.salvar(livro);
        return ResponseEntity.status(201).body(novoLivro);
    }

    @PostMapping("/testar")
    public ResponseEntity<String> testar(){
        return ResponseEntity.ok("Funcionou!!");
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") int id){
        if (livroService.excluir(id)){
            return ResponseEntity.status(204).build();
        }

        Map<String, Object> erro = new HashMap<>();
        erro.put("status", 404);
        erro.put("mensagem", "O livro com ID " + id + " não foi encontrado!");
        erro.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(404).body(erro);

    }

    @PutMapping
    public ResponseEntity<Livro> atualizar(@RequestBody Livro livroAtualizado){

        Livro novoLivro = livroService.atualizar(livroAtualizado);

        if (novoLivro != null){
            return ResponseEntity.status(200).body(novoLivro);
        }

        return ResponseEntity.status(404).build();

    }

}
