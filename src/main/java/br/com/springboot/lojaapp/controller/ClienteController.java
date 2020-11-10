package br.com.springboot.lojaapp.controller;

import br.com.springboot.lojaapp.dto.ClienteDto;
import br.com.springboot.lojaapp.dto.ClienteNewDto;
import br.com.springboot.lojaapp.model.Cliente;
import br.com.springboot.lojaapp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaClientePorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscarClientePorId(id);

        return ResponseEntity.ok(cliente);
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDto>> buscarTodosClientes() {
        List<ClienteDto> clientes = clienteService.buscarTodosCliente();

        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDto>> buscarTodosPaginados(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "5") Integer elementosPorPagina,
            @RequestParam(defaultValue = "ASC") String direcao,
            @RequestParam(defaultValue = "nome") String ordenarPor) {

        Page<ClienteDto> clientes = clienteService.buscarTodosComPaginacao(pagina, elementosPorPagina, direcao,
                ordenarPor);

        return ResponseEntity.ok().body(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody ClienteNewDto clienteNewDto) {
        Cliente cliente = clienteService.salvarCliente(clienteNewDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(cliente);


    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id,
                                                   @Valid @RequestBody ClienteDto clienteDto) {

        clienteService.atualizarCliente(clienteDto, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable Integer id){
        clienteService.deletarCliente(id);

        return ResponseEntity.ok("Categoria Deletada");
    }


}
