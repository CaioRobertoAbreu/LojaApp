package br.com.springboot.lojaapp.controller;

import br.com.springboot.lojaapp.model.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @GetMapping("listar")
    public List<Categoria> listar(){
        return Arrays.asList(new Categoria("Informática", 1), new Categoria("Escritório", 2));
    }

}
