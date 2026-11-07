package br.senac.sp.livraria.service;

import br.senac.sp.livraria.model.Editora;
import br.senac.sp.livraria.repository.EditoraRepository;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {

    private EditoraRepository editoraRepository;

    public EditoraService(EditoraRepository editoraRepository){
        this.editoraRepository = editoraRepository;
    }

    public Editora salvar(Editora editora){
        return editoraRepository.save(editora);
    }

}
