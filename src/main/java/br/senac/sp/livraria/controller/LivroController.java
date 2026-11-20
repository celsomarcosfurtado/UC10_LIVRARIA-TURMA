package br.senac.sp.livraria.controller;

import br.senac.sp.livraria.model.Livro;
import br.senac.sp.livraria.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// POST http://localhost:8080/v1/api/livros
// GET http://localhost:8080/v1/api/livros

@RestController
@RequestMapping("/v1/api/livros")
@Tag(name = "Livros", description = "API responsável por gerenciar os livros da editora. Com essa API será possível inserir, atualizar, listar e excluir livros do sistema.")
public class LivroController {

    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar novo livro",
            description = "Criar o registro de um novo livro no sistema."
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Livro cadastrado com sucesso!"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados de entrada inválidos.",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Livro> salvar(@Valid @RequestBody Livro livro) {
        System.out.println("Endpoint chamado...");
        Livro novoLivro = livroService.salvar(livro);
        return ResponseEntity.status(201).body(novoLivro);
    }

    @GetMapping
    @Operation(summary = "Listar todos os livros do sistema.")
    public ResponseEntity<List<Livro>> listarLivros() {
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable("codigo") int id) {
        Optional<Livro> livroEncontrado = livroService.buscarPorId(id);

        if (livroEncontrado.isPresent()) {
            return ResponseEntity.ok(livroEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ano-edicao")
    public ResponseEntity<List<Livro>> findByAnoEdicaoLessThan(
            @RequestParam("anoEdicao") int anoEdicao
    ) {
        return ResponseEntity.status(200).body(livroService.findByAnoEdicaoLessThan(anoEdicao));
    }

    @GetMapping("/autor")
    public ResponseEntity<List<Livro>> findByAutorStartingWith(
            @RequestParam("autor") String autor
    ) {
        return ResponseEntity.status(200).body(livroService.findByAutorStartingWith(autor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") int id) {
        if (livroService.excluir(id)) {
            return ResponseEntity.status(204).build();
        }

        Map<String, Object> erro = new HashMap<>();
        erro.put("status", 404);
        erro.put("mensagem", "O livro com ID " + id + " não foi encontrado!");
        erro.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(404).body(erro);

    }

    @GetMapping("/faixa-preco/")
    @Operation(
            summary = "Lista livros por faixa de preços.",
            description = "Listar livros que estejam dentro de uma faixa de preços. Por exemplo: Listar todos os livros que custam entre R$20,00 e R$50,00."
    )
    public ResponseEntity<List<Livro>> listarPrecoPor(
            @Parameter(name = "valorInicial", description = "Informe o valor inicial da faixa de preços que você deseja consultar.", example = "20.00")
            @RequestParam("valorInicial")
            double valorInicial,
            @Parameter(name = "valorFinal", description = "Informe o valor final da faixa de preços que você deseja consultar.", example = "50.00")
            @RequestParam("valorFinal")
            double valorFinal
    ) {
        return ResponseEntity.status(200).body(livroService.listarPrecoEntre(valorInicial, valorFinal));
    }

    @PutMapping
    @Operation(
            method = "PUT",
            summary = "Atualizar dados de um livro.",
            description = "Este endpoint é utilizado para atualizar os dados de um livro. Para encontrar o livro que será atualizado a busca será efetuada através do ID do livro."
    )
    public ResponseEntity<Livro> atualizar(@RequestBody Livro livroAtualizado) {

        Livro novoLivro = livroService.atualizar(livroAtualizado);

        if (novoLivro != null) {
            return ResponseEntity.status(200).body(novoLivro);
        }

        return ResponseEntity.status(404).build();

    }

}