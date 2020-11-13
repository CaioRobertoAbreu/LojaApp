package br.com.springboot.lojaapp.service;

import br.com.springboot.lojaapp.dto.ProdutoDto;
import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.model.Produto;
import br.com.springboot.lojaapp.repository.CategoriaRepository;
import br.com.springboot.lojaapp.repository.ProdutoRepository;
import br.com.springboot.lojaapp.service.exception.ObjectNotFoundException;
import br.com.springboot.lojaapp.service.utils.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository) {

        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto buscarPorId(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado. Id: " +
                id + ". Tipo: " + Produto.class.getName()));
    }

    public Page<ProdutoDto> buscarPorFiltro(String nomeProduto, String categoria,
                                         Integer pagina, Integer elementosPorPagina,
                                         String ordem, String direcao) {

        String nomeProdutoDecodificado = URI.decodeString(nomeProduto);

        List<Integer> categoriasListaInteger = URI.CategoriasLista(categoria);
        List<Categoria> categoriasEncontadas = categoriaRepository.findAllById(categoriasListaInteger);

        PageRequest paginacao = PageRequest.of(pagina, elementosPorPagina, Sort.Direction.valueOf(direcao),
                ordem);

        Page<Produto> produtos = produtoRepository.buscaFiltrada(nomeProdutoDecodificado, categoriasEncontadas,
                paginacao);

        return produtos.map(ProdutoDto::new);
    }
}
