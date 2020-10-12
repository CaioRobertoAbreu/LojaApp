package br.com.springboot.lojaapp.service;

import br.com.springboot.lojaapp.model.Pedido;
import br.com.springboot.lojaapp.repository.PedidoRepository;
import br.com.springboot.lojaapp.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscarPorId(Integer id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return  pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado. Id:" +
                id + ". Tipo: " + Pedido.class.getName()));
    }
}
