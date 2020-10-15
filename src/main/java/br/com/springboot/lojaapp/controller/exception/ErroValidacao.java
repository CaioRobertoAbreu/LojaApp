package br.com.springboot.lojaapp.controller.exception;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadrao {
    private static final long serialVersionUID = -2198620798909172677L;

    private List<CampoComErro> errosList = new ArrayList<>();

    public ErroValidacao(Integer status, LocalTime momento, Long timeStamp, String mensagem) {
        super(status, momento, timeStamp, mensagem);
    }

    public List<CampoComErro> getErrosList() {
        return errosList;
    }

    public void addErro(String campo, String mensagem) {
        this.errosList.add(new CampoComErro(campo, mensagem));
    }
}
