package br.com.springboot.lojaapp.controller;

import br.com.springboot.lojaapp.model.Cliente;
import br.com.springboot.lojaapp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> buscaClientePorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscarClientePorId(id);

        return ResponseEntity.ok(cliente);
    }
}
