package br.com.springboot.lojaapp.service;

import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id) {
        Optional<Categoria> objeto = categoriaRepository.findById(id);
        return objeto.orElse(null);
    }
}
