package br.com.springboot.lojaapp.controller;

import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> listar(@PathVariable Integer id){
        Categoria categoria = categoriaService.buscar(id);

        return ResponseEntity.ok().body(categoria);

    }

}
