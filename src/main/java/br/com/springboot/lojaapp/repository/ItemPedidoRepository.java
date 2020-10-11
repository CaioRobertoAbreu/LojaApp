package br.com.springboot.lojaapp.repository;

import br.com.springboot.lojaapp.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
