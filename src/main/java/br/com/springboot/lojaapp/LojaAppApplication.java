package br.com.springboot.lojaapp;

import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.model.Produto;
import br.com.springboot.lojaapp.repository.CategoriaRepository;
import br.com.springboot.lojaapp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class LojaAppApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

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

    }
}
