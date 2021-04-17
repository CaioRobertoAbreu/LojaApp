package br.com.springboot.lojaapp.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum PerfilUsuario implements GrantedAuthority {

    ADMIN(100, "ROLE_ADMIN", "Administrador"),
    CLIENTE(200, "ROLE_CLIENTE", "Cliente");

    private final Integer codigo;
    private final String descricao;
    private final String authority;

    PerfilUsuario(Integer codigo, String descricao, String authority){
        this.codigo = codigo;
        this.descricao = descricao;
        this.authority = authority;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public static PerfilUsuario toEnum(Integer codigo){

        if(codigo == null) {
            return null;
        }

        for (PerfilUsuario perfil : PerfilUsuario.values()){
            if(codigo.equals(perfil.getCodigo())){
                return perfil;
            }
        }

        throw new IllegalArgumentException("Não existe enumerador com este código" + codigo);
    }

}
