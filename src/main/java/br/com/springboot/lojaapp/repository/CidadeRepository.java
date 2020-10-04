package br.com.springboot.lojaapp.repository;

import br.com.springboot.lojaapp.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
