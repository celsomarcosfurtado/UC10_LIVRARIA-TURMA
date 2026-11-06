package br.senac.sp.livraria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "tb_editora")
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "url_site")
    private String urlSite;

    @Email(message = "O e-mail informado está incorreto!")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlSite() {
        return urlSite;
    }

    public void setUrlSite(String urlSite) {
        this.urlSite = urlSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
