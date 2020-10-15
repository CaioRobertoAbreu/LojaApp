package br.com.springboot.lojaapp.service;

import br.com.springboot.lojaapp.dto.CategoriaDto;
import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.repository.CategoriaRepository;
import br.com.springboot.lojaapp.service.exception.DataIntegrityException;
import br.com.springboot.lojaapp.service.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.*;

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

    public List<CategoriaDto> buscarTodos(){
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }

   public Page<Categoria> buscarTodosComPaginacao(Integer pagina, Integer elementosPorPagina, String direcao,
                                      String odenarPor) {
       PageRequest paginacao = PageRequest.of(pagina, elementosPorPagina, Direction.valueOf(direcao), odenarPor);

       return categoriaRepository.findAll(paginacao);
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
        try{
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Não é possível excluir categoria que contém produtos");
        }
    }
}
