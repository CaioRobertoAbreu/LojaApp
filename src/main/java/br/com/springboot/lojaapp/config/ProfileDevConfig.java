package br.com.springboot.lojaapp.config;

import br.com.springboot.lojaapp.service.BancoDadosService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = "dev")
public class ProfileDevConfig {

    private final BancoDadosService bancoDadosService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    public ProfileDevConfig(BancoDadosService bancoDadosService) {
        this.bancoDadosService = bancoDadosService;
    }

    @Bean
    public boolean inserirDadosBancoDados(){

        if (strategy.equals("create")) {
            bancoDadosService.instanciarDados();
            return true;
        }

        return false;
    }
}
