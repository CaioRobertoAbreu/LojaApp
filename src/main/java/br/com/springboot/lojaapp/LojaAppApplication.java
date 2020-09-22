package br.com.springboot.lojaapp;

import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class LojaAppApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;;

    public static void main(String[] args) {
        SpringApplication.run(LojaAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Salvando as categorias no bd h2
        Categoria categoria1 = new Categoria("Informatica", null);
        Categoria categoria2 = new Categoria("Escritorio", null);
        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));

    }
}
