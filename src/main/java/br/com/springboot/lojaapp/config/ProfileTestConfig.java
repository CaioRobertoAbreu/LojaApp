package br.com.springboot.lojaapp.config;

import br.com.springboot.lojaapp.service.BancoDadosService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = "test")
public class ProfileTestConfig {

    private final BancoDadosService bancoDadosService;

    public ProfileTestConfig(BancoDadosService bancoDadosService) {
        this.bancoDadosService = bancoDadosService;
    }

    @Bean
    public boolean inserirDadosBancoDados() {

        bancoDadosService.instanciarDados();

        return true;
    }
}
