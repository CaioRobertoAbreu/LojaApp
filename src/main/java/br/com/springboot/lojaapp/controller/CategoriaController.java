package br.com.springboot.lojaapp.controller;

import br.com.springboot.lojaapp.dto.CategoriaDto;
import br.com.springboot.lojaapp.model.Categoria;
import br.com.springboot.lojaapp.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> buscarTodos() {
        List<CategoriaDto> categoriaDtos = categoriaService.buscarTodos();

        return ResponseEntity.ok(categoriaDtos);
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

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDto>> buscarTodosPaginado(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "5") Integer elementosPorPagina,
            @RequestParam(defaultValue = "ASC") String direcao,
            @RequestParam(defaultValue = "nome") String ordenarPor) {

        Page<Categoria> categorias = categoriaService.buscarTodosComPaginacao(pagina, elementosPorPagina,
                direcao, ordenarPor);

        Page<CategoriaDto> categoriaDtos = categorias.map(CategoriaDto::new);

        return ResponseEntity.ok(categoriaDtos);
    }
}