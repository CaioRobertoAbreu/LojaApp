package br.com.springboot.lojaapp.controller;

import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Integer id){
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping()
    public ResponseEntity<List<Categoria>> listarTodos(){
        List<Categoria> categorias = categoriaService.buscarTodos();

        return ResponseEntity.ok().body(categorias);
    }


    @PostMapping
    public ResponseEntity<Categoria> inserirCategoria(@RequestBody Categoria categoria){
        Categoria categoriaInserida = categoriaService.salvarCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(categoriaInserida.getId()).toUri();

        return ResponseEntity.created(uri).body(categoriaInserida);
    }
}
