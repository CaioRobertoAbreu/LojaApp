package br.com.springboot.lojaapp.service;

import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.repository.CategoriaRepository;
import br.com.springboot.lojaapp.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> objeto = categoriaRepository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada. Id: " +
                id + ". Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> buscarTodos(){
        return categoriaRepository.findAll();
    }

    public Categoria salvarCategoria(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizarCategoria(Categoria categoria, Integer id){
        categoria.setId(id);
        buscarPorId(id);
        return categoriaRepository.save(categoria);
    }


    public void deletarCategoria(Integer id) {
        buscarPorId(id);
        categoriaRepository.deleteById(id);
    }
}
