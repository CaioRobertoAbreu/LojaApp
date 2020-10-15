package br.com.springboot.lojaapp.controller.exception;

import java.io.Serializable;

public class CampoComErro implements Serializable {
    private static final long serialVersionUID = 4873396470386478585L;

    private String campo;
    private String mensagem;

    public CampoComErro(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
