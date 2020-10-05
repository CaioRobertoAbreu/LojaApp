package br.com.springboot.lojaapp.model.enums;

public enum TipoCliente {

    PESSOA_FISICA(100, "Pessoa Física"),
    PESSOA_JURIDICA(200, "Pessoa Jurídica");

    private final Integer codigo;
    private final String descricao;

    TipoCliente(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod){
        if(cod == null) {
            return null;
        }

        for(TipoCliente tipo : TipoCliente.values()){
            if(tipo.getCodigo().equals(cod)){
                return tipo;
            }
        }

        throw new IllegalArgumentException("Não existe enumerador com este código");
    }

}
