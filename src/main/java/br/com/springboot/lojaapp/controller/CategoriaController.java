package br.com.springboot.lojaapp.controller;

import br.com.springboot.lojaapp.dto.CategoriaDto;
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
    public ResponseEntity<Categoria> listarPorId(@PathVariable Integer id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping()
    public ResponseEntity<List<CategoriaDto>> listarTodos() {
        List<CategoriaDto> categorias = categoriaService.buscarTodos();

        return ResponseEntity.ok().body(categorias);
    }


    @PostMapping
    public ResponseEntity<Categoria> inserirCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaInserida = categoriaService.salvarCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(categoriaInserida.getId()).toUri();

        return ResponseEntity.created(uri).body(categoriaInserida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer id,
                                                        @RequestBody Categoria categoria) {
        categoriaService.atualizarCategoria(categoria, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable Integer id) {
        categoriaService.deletarCategoria(id);

        return ResponseEntity.ok("Categoria Deletada");

    }
}