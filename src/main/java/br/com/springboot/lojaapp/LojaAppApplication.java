package br.com.springboot.lojaapp;

import br.com.springboot.lojaapp.model.*;
import br.com.springboot.lojaapp.model.enums.EstadoPagamento;
import br.com.springboot.lojaapp.model.enums.PerfilUsuario;
import br.com.springboot.lojaapp.model.enums.TipoCliente;
import br.com.springboot.lojaapp.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.util.Arrays.asList;

@SpringBootApplication
public class LojaAppApplication implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PedidoRepositoty pedidoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public LojaAppApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
                              CidadeRepository cidadeRepository, EstadoRepository estadoRepository,
                              ClienteRepository clienteRepository, EnderecoRepository enderecoRepository,
                              PedidoRepositoty pedidoRepository, PagamentoRepository pagamentoRepository,
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

        Categoria categoria1 = new Categoria("Informatica", null);
        Categoria categoria2 = new Categoria("Escritorio", null);
        Categoria categoria3 = new Categoria("Cama, mesa e banho", null);
        Categoria categoria4 = new Categoria("Telefone e celulares", null);
        Categoria categoria5 = new Categoria("Eletrodomesticos", null);
        Categoria categoria6 = new Categoria("Móveis", null);
        Categoria categoria7 = new Categoria("Automotivo", null);
        Categoria categoria8 = new Categoria("Bebe e criança", null);
        Categoria categoria9 = new Categoria("Esportes", null);
        Categoria categoria10 = new Categoria("Decoracao", null);


        Produto produto1 = new Produto(null, "Computador", 2000.);
        Produto produto2 = new Produto(null, "Impressora", 800.);
        Produto produto3 = new Produto(null, "Mouse", 80.);
        Produto produto4 = new Produto(null, "Mesa de Escritorio", 300.);
        Produto produto5 = new Produto(null, "Toalha", 50.);
        Produto produto6 = new Produto(null, "Colcha", 200.);
        Produto produto7 = new Produto(null, "Tv True Color", 1200.);
        Produto produto8 = new Produto(null, "Roçadeira", 300.);
        Produto produto9 = new Produto(null, "Abajour", 100.);
        Produto produto10 = new Produto(null, "Pendente", 180.);
        Produto produto11 = new Produto(null, "Shampoo Automotivo", 90.);

        categoria1.getProdutos().addAll(asList(produto1, produto2,produto3));
        categoria2.getProdutos().addAll(asList(produto2, produto4));
        categoria3.getProdutos().addAll(asList(produto5, produto6));
        categoria4.getProdutos().addAll(asList(produto1, produto2, produto3, produto7));
        categoria5.getProdutos().add(produto8);
        categoria6.getProdutos().addAll(asList(produto9, produto10));
        categoria7.getProdutos().add(produto11);

        produto1.getCategorias().addAll(asList(categoria1, categoria4));
        produto2.getCategorias().addAll(asList(categoria1, categoria2, categoria4));
        produto3.getCategorias().addAll(asList(categoria1, categoria4));
        produto4.getCategorias().add(categoria2);
        produto5.getCategorias().add(categoria3);
        produto6.getCategorias().add(categoria3);
        produto7.getCategorias().add(categoria4);
        produto8.getCategorias().add(categoria5);
        produto9.getCategorias().add(categoria6);
        produto10.getCategorias().add(categoria6);
        produto11.getCategorias().add(categoria7);

        categoriaRepository.saveAll(asList(categoria1, categoria2, categoria3, categoria4, categoria5,
                categoria6, categoria7, categoria8, categoria9, categoria10));

        produtoRepository.saveAll(asList(produto1, produto2,produto3, produto4, produto5, produto6,
                produto7, produto8, produto9, produto10, produto11));

        Estado estado1 = new Estado(null, "Minas Gerais");
        Estado estado2 = new Estado(null, "São Paulo");

        Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
        Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
        Cidade cidade3 = new Cidade(null, "Capinas", estado2);

        estado1.getCidades().add(cidade1);
        estado2.getCidades().addAll(asList(cidade2, cidade3));

        estadoRepository.saveAll(asList(estado1, estado2));
        cidadeRepository.saveAll(asList(cidade1, cidade2, cidade3));

        Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com",
                new BCryptPasswordEncoder().encode("SenhaSecreta"), "12345678911", TipoCliente.PESSOA_FISICA);
        cliente1.getTelefones().addAll(asList("1354157199", "13654237624"));

        //Cliente com perfil ADMIN para teste
        Cliente cliente2 = new Cliente(null, "Caio Cesar", "caiocesar@gmail.com",
                new BCryptPasswordEncoder().encode("SenhaSecreta"), "35444630087", TipoCliente.PESSOA_FISICA);
        cliente1.getTelefones().addAll(asList("1375412345", "1367128381"));
        cliente2.addPerfil(PerfilUsuario.ADMIN);

        Endereco endereco1 = new Endereco(null, "Rua Flores", 300, "apto 203",
                "Jardim", "38220834", cliente1, cidade1);

        Endereco endereco2 = new Endereco(null, "Av Matos", 105, "sala 800",
                "Centro", "38220834", cliente1, cidade2);

        Endereco endereco3 = new Endereco(null, "Av Samambaia", 6541, null,
                "Centro", "93456412", cliente2, cidade2);

        cliente1.getEnderecos().addAll(asList(endereco1, endereco2));
        cliente2.getEnderecos().add(endereco3);

        clienteRepository.saveAll(asList(cliente1, cliente2));
        enderecoRepository.saveAll(asList(endereco1, endereco2, endereco3));

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
