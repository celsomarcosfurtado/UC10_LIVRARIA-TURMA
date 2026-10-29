package br.senac.sp.livraria.model;

import jakarta.persistence.*;

// Mapeando a classe Livro com a tabela tbl_livro no banco de dados
@Entity(name = "tb_livro")
public class Livro {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private int id;

        private String titulo;
        private String autor;
        private String editora;
        private double preco;

        @Column(name = "ano_edicao")
        private int anoEdicao;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getTitulo() {
                return titulo;
        }

        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        public String getAutor() {
                return autor;
        }

        public void setAutor(String autor) {
                this.autor = autor;
        }

        public String getEditora() {
                return editora;
        }

        public void setEditora(String editora) {
                this.editora = editora;
        }

        public double getPreco() {
                return preco;
        }

        public void setPreco(double preco) {
                this.preco = preco;
        }

        public int getAnoEdicao() {
                return anoEdicao;
        }

        public void setAnoEdicao(int anoEdicao) {
                this.anoEdicao = anoEdicao;
        }
}

