package br.com.springboot.lojaapp;

import br.com.springboot.lojaapp.model.*;
import br.com.springboot.lojaapp.model.enums.EstadoPagamento;
import br.com.springboot.lojaapp.model.enums.TipoCliente;
import br.com.springboot.lojaapp.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.util.Arrays.asList;

@SpringBootApplication
public class LojaAppApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(LojaAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
