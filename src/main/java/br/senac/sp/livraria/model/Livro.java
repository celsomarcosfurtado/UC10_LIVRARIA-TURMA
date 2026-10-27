package br.senac.sp.livraria.model;

public record Livro(
        int id,
        String titulo,
        String autor,
        String editora,
        double preco,
        int anoEdicao
) {
}
