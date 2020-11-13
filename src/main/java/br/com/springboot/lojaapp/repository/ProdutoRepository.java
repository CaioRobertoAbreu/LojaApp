package br.com.springboot.lojaapp.repository;

import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT p FROM Produto p " +
            "INNER JOIN p.categorias cat " +
            "WHERE p.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> buscaFiltrada(@Param(value = "nome") String nomeProduto,
                       @Param(value = "categorias") List<Categoria> categoriasEncontadas,
                       Pageable paginacao);
}
