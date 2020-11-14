package br.com.springboot.lojaapp.service;

import br.com.springboot.lojaapp.model.ItemPedido;
import br.com.springboot.lojaapp.model.PagamentoComBoleto;
import br.com.springboot.lojaapp.model.Pedido;
import br.com.springboot.lojaapp.model.Produto;
import br.com.springboot.lojaapp.model.enums.EstadoPagamento;
import br.com.springboot.lojaapp.repository.ItemPedidoRepository;
import br.com.springboot.lojaapp.repository.PagamentoRepository;
import br.com.springboot.lojaapp.repository.PedidoRepositoty;
import br.com.springboot.lojaapp.repository.ProdutoRepository;
import br.com.springboot.lojaapp.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepositoty pedidoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoComBoletoService pagamentoComBoletoService;
    private final ProdutoService produtoService;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepositoty pedidoRepository,
                         PagamentoRepository pagamentoRepository,
                         PagamentoComBoletoService pagamentoComBoletoService,
                         ProdutoService produtoService,
                         ItemPedidoRepository itemPedidoRepository) {

        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoComBoletoService = pagamentoComBoletoService;
        this.produtoService = produtoService;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public Pedido buscarPorId(Integer id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return  pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado. Id: " +
                id + ". Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido salvarPedido(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(LocalDateTime.now());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if(pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
            pagamentoComBoletoService.atrubuirDataVencimento(pagamento, pedido.getInstante());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for(ItemPedido itens : pedido.getItens()){
            itens.setDesconto(0.0);
            Produto produto = produtoService.buscarPorId(itens.getProduto().getId());
            itens.setPreco(produto.getPreco());
            itens.getId().setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;
    }
}
