package br.senac.sp.livraria.service;

import br.senac.sp.livraria.model.Livro;
import br.senac.sp.livraria.repository.LivroRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ext.jdk8.OptionalDoubleDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro){
        return livroRepository.save(livro);
    }

    public List<Livro> listarLivros(){
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(int id){
        livroRepository.deleteById(id);
        return livroRepository.findById(id);
    }

}
