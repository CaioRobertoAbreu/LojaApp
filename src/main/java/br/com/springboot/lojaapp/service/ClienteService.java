package br.com.springboot.lojaapp.service;

import br.com.springboot.lojaapp.dto.ClienteDto;
import br.com.springboot.lojaapp.dto.ClienteNewDto;
import br.com.springboot.lojaapp.model.Cidade;
import br.com.springboot.lojaapp.model.Cliente;
import br.com.springboot.lojaapp.model.Endereco;
import br.com.springboot.lojaapp.model.enums.TipoCliente;
import br.com.springboot.lojaapp.repository.ClienteRepository;
import br.com.springboot.lojaapp.repository.EnderecoRepository;
import br.com.springboot.lojaapp.service.exception.DataIntegrityException;
import br.com.springboot.lojaapp.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;

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


    public Cliente salvarCliente(ClienteNewDto clienteNewDto) {
        Cliente cliente = toCliente(clienteNewDto);
        clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());

        return cliente;
    }

    private Cliente toCliente(ClienteNewDto clienteNewDto){
        Cidade cidade = new Cidade();
        cidade.setId(clienteNewDto.getCidadeId());

        Endereco endereco = new Endereco();
        endereco.setBairro(clienteNewDto.getBairro());
        endereco.setCep(clienteNewDto.getCep());
        endereco.setComplemento(clienteNewDto.getComplemento());
        endereco.setLogradouro(clienteNewDto.getLogradouro());
        endereco.setNumero(clienteNewDto.getNumero());
        endereco.setCidade(cidade);

        Cliente cliente = new Cliente();

        cliente.setNome(clienteNewDto.getNome());
        cliente.setEmail(clienteNewDto.getEmail());
        cliente.setCpf_Cnpj(clienteNewDto.getCpf_Cnpj());
        cliente.setTipoCliente(TipoCliente.toEnum(clienteNewDto.getTipoCliente()));
        cliente.getEnderecos().add(endereco);

        cliente.getTelefones().add(clienteNewDto.getTelefone1());
        if(clienteNewDto.getTelefone2() != null) {
            cliente.getTelefones().add(clienteNewDto.getTelefone2());
        }
        if(clienteNewDto.getTelefone3() != null) {
            cliente.getTelefones().add(clienteNewDto.getTelefone3());
        }

        endereco.setCliente(cliente);

        return cliente;
    }
}
