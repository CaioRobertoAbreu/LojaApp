package br.com.springboot.lojaapp.dto;

import br.com.springboot.lojaapp.service.validation.InserirCliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@InserirCliente
public class ClienteNewDto implements Serializable {
    private static final long serialVersionUID = -1L;

    @NotEmpty(message = "Campo Obrigatorio")
    @Size(min = 5, max = 50)
    private String nome;
    @NotEmpty(message = "Campo Obrigatorio")
    @Email(message = "e-mail inválido")
    private String email;
    @NotEmpty(message = "preenchimento obrigatório")
    private String cpf_Cnpj;
    @NotNull(message = "campo obrigatório")
    private Integer tipoCliente;

    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cep;
    @NotEmpty(message = "campo obrigatorio")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;

    public ClienteNewDto() {
    }

    public ClienteNewDto(String nome, String email, String cpf_Cnpj, Integer tipoCliente,
                         String logradouro, Integer numero, String complemento, String bairro,
                         String cep, String telefone1, String telefone2, String telefone3, Integer cidadeId) {

        this.nome = nome;
        this.email = email;
        this.cpf_Cnpj = cpf_Cnpj;
        this.tipoCliente = tipoCliente;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.telefone3 = telefone3;
        this.cidadeId = cidadeId;
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

    public String getCpf_Cnpj() {
        return cpf_Cnpj;
    }

    public void setCpf_Cnpj(String cpf_Cnpj) {
        this.cpf_Cnpj = cpf_Cnpj;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
