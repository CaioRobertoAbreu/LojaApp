package br.com.springboot.lojaapp.model.enums;

public enum EstadoPagamento {

    PENDENTE(101, "Pendente"),
    QUITADO(201, "Quitado"),
    CANCELADO(301, "Cancelado");

    private final Integer codigo;
    private final String descricao;

    EstadoPagamento(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer codigo){

        if(codigo == null) {
            return null;
        }

        for (EstadoPagamento estado : EstadoPagamento.values()){
            if(codigo.equals(estado.getCodigo())){
                return estado;
            }
        }

        throw new IllegalArgumentException("Não existe enumerador com este código" + codigo);
    }
}
