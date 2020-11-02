package br.com.springboot.lojaapp.service;

import br.com.springboot.lojaapp.dto.ClienteDto;
import br.com.springboot.lojaapp.model.Cliente;
import br.com.springboot.lojaapp.repository.ClienteRepository;
import br.com.springboot.lojaapp.service.exception.DataIntegrityException;
import br.com.springboot.lojaapp.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente buscarClientePorId(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado. Id:" +
                id + ". Tipo: " + Cliente.class.getName()));

    }

    public List<ClienteDto> buscarTodosCliente() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
    }

    public void atualizarCliente(ClienteDto clienteDto, Integer id) {
        clienteDto.setId(id);
        Cliente cliente = buscarClientePorId(clienteDto.getId());
        atualizaDadosCliente(cliente, clienteDto);
        clienteRepository.save(cliente);

    }

    private void atualizaDadosCliente(Cliente cliente, ClienteDto clienteDto) {
        cliente.setNome(clienteDto.getNome());
        cliente.setEmail(clienteDto.getEmail());
    }

    public Page<ClienteDto> buscarTodosComPaginacao(Integer pagina,
                                                 Integer elementosPorPagina,
                                                 String direcao, String ordenarPor) {

        PageRequest page = PageRequest.of(pagina, elementosPorPagina, Sort.Direction.valueOf(direcao),
                ordenarPor);

        Page<Cliente> clientes = clienteRepository.findAll(page);

        return clientes.map(ClienteDto::new);
    }

    public void deletarCliente(Integer id) {
        buscarClientePorId(id);
        try{
            clienteRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir cliente que contém endereço"
            + " e pedidos.");
        }
    }


}
