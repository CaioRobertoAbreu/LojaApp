package br.com.springboot.lojaapp;

import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.model.Cidade;
import br.com.springboot.lojaapp.model.Estado;
import br.com.springboot.lojaapp.model.Produto;
import br.com.springboot.lojaapp.repository.CategoriaRepository;
import br.com.springboot.lojaapp.repository.CidadeRepository;
import br.com.springboot.lojaapp.repository.EstadoRepository;
import br.com.springboot.lojaapp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.Arrays.asList;

@SpringBootApplication
public class LojaAppApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    public static void main(String[] args) {
        SpringApplication.run(LojaAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Salvando as categorias no bd h2
        Categoria categoria1 = new Categoria("Informatica", null);
        Categoria categoria2 = new Categoria("Escritorio", null);

        Produto p1 = new Produto(null, "Computador", 2000.);
        Produto p2 = new Produto(null, "Impressora", 800.);
        Produto p3 = new Produto(null, "Mouse", 80.);

        categoria1.getProdutos().addAll(asList(p1, p2,p3));
        categoria2.getProdutos().add(p2);

        p1.getCategorias().add(categoria1);
        p2.getCategorias().addAll(asList(categoria1, categoria2));
        p3.getCategorias().add(categoria1);

        categoriaRepository.saveAll(asList(categoria1, categoria2));
        produtoRepository.saveAll(asList(p1, p2,p3));

        Estado estado1 = new Estado(null, "Minas Gerais");
        Estado estado2 = new Estado(null, "São Paulo");

        Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
        Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
        Cidade cidade3 = new Cidade(null, "Capinas", estado2);

        estado1.getCidades().add(cidade1);
        estado2.getCidades().addAll(asList(cidade2, cidade3));

        estadoRepository.saveAll(asList(estado1, estado2));
        cidadeRepository.saveAll(asList(cidade1, cidade2, cidade3));



    }
}
