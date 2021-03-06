package br.com.springboot.lojaapp.model;

import br.com.springboot.lojaapp.model.enums.PerfilUsuario;
import br.com.springboot.lojaapp.model.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    @JsonIgnore
    private String senha;
    @Column(unique = true)
    private String cpf_Cnpj;
    private Integer tipoCliente;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "Perfis")
    private Set<Integer> perfis = new HashSet<>();
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String email, String senha, String cpf_Cnpj, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf_Cnpj = cpf_Cnpj;
        this.tipoCliente = tipoCliente.getCodigo();
        addPerfil(PerfilUsuario.CLIENTE);
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf_Cnpj() {
        return cpf_Cnpj;
    }

    public void setCpf_Cnpj(String cpf_Cnpj) {
        this.cpf_Cnpj = cpf_Cnpj;
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(this.tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        if(tipoCliente != null) {
            this.tipoCliente = tipoCliente.getCodigo();
        } else {
            this.tipoCliente = null;
        }
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public void getPerfisUsuario() {
        return ;
    }

    public void addPerfil(PerfilUsuario perfilUsuario){
        this.perfis.add(perfilUsuario.getCodigo());
    }

    public Set<String> getPerfis() {
        return this.perfis
                .stream()
                .map(p -> PerfilUsuario.toEnum(p).getAuthority())
                .collect(Collectors.toSet());
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return getId().equals(cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
