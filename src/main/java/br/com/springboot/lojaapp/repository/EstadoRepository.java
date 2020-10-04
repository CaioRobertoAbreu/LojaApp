package br.com.springboot.lojaapp.repository;

import br.com.springboot.lojaapp.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
