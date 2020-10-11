package br.com.springboot.lojaapp;

import br.com.springboot.lojaapp.model.*;
import br.com.springboot.lojaapp.model.enums.EstadoPagamento;
import br.com.springboot.lojaapp.model.enums.TipoCliente;
import br.com.springboot.lojaapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import static java.util.Arrays.asList;

@SpringBootApplication
public class LojaAppApplication implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public LojaAppApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
                              CidadeRepository cidadeRepository, EstadoRepository estadoRepository,
                              ClienteRepository clienteRepository, EnderecoRepository enderecoRepository,
                              PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository,
                              ItemPedidoRepository itemPedidoRepository) {

        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LojaAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Salvando as categorias no bd h2
        Categoria categoria1 = new Categoria("Informatica", null);
        Categoria categoria2 = new Categoria("Escritorio", null);

        Produto produto1 = new Produto(null, "Computador", 2000.);
        Produto produto2 = new Produto(null, "Impressora", 800.);
        Produto produto3 = new Produto(null, "Mouse", 80.);

        categoria1.getProdutos().addAll(asList(produto1, produto2,produto3));
        categoria2.getProdutos().add(produto2);

        produto1.getCategorias().add(categoria1);
        produto2.getCategorias().addAll(asList(categoria1, categoria2));
        produto3.getCategorias().add(categoria1);

        categoriaRepository.saveAll(asList(categoria1, categoria2));
        produtoRepository.saveAll(asList(produto1, produto2,produto3));

        Estado estado1 = new Estado(null, "Minas Gerais");
        Estado estado2 = new Estado(null, "São Paulo");

        Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
        Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
        Cidade cidade3 = new Cidade(null, "Capinas", estado2);

        estado1.getCidades().add(cidade1);
        estado2.getCidades().addAll(asList(cidade2, cidade3));

        estadoRepository.saveAll(asList(estado1, estado2));
        cidadeRepository.saveAll(asList(cidade1, cidade2, cidade3));

        Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678911",
                TipoCliente.PESSOA_FISICA);
        cliente1.getTelefones().addAll(asList("1354157199", "13654237624"));

        Endereco endereco1 = new Endereco(null, "Rua Flores", 300, "apto 203",
                "Jardim", "38220834", cliente1, cidade1);

        Endereco endereco2 = new Endereco(null, "Av Matos", 105, "sala 800",
                "Centro", "38220834", cliente1, cidade2);

        cliente1.getEnderecos().addAll(asList(endereco1, endereco2));

        clienteRepository.save(cliente1);
        enderecoRepository.saveAll(asList(endereco1, endereco2));

        Pedido pedido1 = new Pedido(null, LocalDateTime.now(), cliente1, endereco1);
        Pedido pedido2 = new Pedido(null, LocalDateTime.now(), cliente1, endereco2);

        Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
        pedido1.setPagamento(pagamento1);

        Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
                LocalDate.now().plusDays(7), null);
        pedido2.setPagamento(pagamento2);

        cliente1.getPedidos().addAll(asList(pedido1, pedido2));

        pedidoRepository.saveAll(asList(pedido1, pedido2));
        pagamentoRepository.saveAll(asList(pagamento1, pagamento2));

        ItemPedido itemPedido1 = new ItemPedido(produto1, pedido1, 1, 0., 2000.);
        ItemPedido itemPedido2 = new ItemPedido(produto3, pedido1, 2, 0., 80.);
        ItemPedido itemPedido3 = new ItemPedido(produto2, pedido2, 1, 100., 800.);

        produto1.getPedidos().add(itemPedido1);
        produto2.getPedidos().add(itemPedido3);
        produto3.getPedidos().add(itemPedido2);

        pedido1.getItens().addAll(asList(itemPedido1, itemPedido2));
        pedido2.getItens().add(itemPedido3);

        itemPedidoRepository.saveAll(asList(itemPedido1, itemPedido2, itemPedido3));


    }
}
