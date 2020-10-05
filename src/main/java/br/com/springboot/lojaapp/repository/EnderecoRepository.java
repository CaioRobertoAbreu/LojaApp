package br.com.springboot.lojaapp.repository;

import br.com.springboot.lojaapp.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
