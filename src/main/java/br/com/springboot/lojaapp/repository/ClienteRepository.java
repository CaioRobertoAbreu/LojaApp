package br.com.springboot.lojaapp.repository;

import br.com.springboot.lojaapp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
