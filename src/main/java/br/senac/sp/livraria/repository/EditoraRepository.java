package br.senac.sp.livraria.repository;

import br.senac.sp.livraria.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Integer> {
}
