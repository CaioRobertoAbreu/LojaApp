package br.com.springboot.lojaapp.dto;

import br.com.springboot.lojaapp.model.Cliente;
import br.com.springboot.lojaapp.service.validation.AtualizarCliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@AtualizarCliente
public class ClienteDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "campo obrigatorio")
    @Length(min = 5, max = 120)
    private String nome;
    @NotEmpty(message = "campo obrigatorio")
    @Email(message = "e-mail invalido")
    private String email;

    public ClienteDto() {
    }

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
