package br.com.springboot.lojaapp.service;

import br.com.springboot.lojaapp.model.Cliente;
import br.com.springboot.lojaapp.repository.ClienteRepository;
import br.com.springboot.lojaapp.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente buscarClientePorId(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado. Id:" +
                id + ". Tipo: " + Cliente.class.getName()));

    }

}
