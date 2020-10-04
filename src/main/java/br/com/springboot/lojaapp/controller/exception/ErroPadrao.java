package br.com.springboot.lojaapp.controller.exception;

import java.io.Serializable;
import java.time.LocalTime;

public class ErroPadrao implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private LocalTime momento;
    private Long timeStamp;
    private String mensagem;

    public ErroPadrao(Integer status, LocalTime momento, Long timeStamp, String mensagem) {
        this.status = status;
        this.momento = momento;
        this.timeStamp = timeStamp;
        this.mensagem = mensagem;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStatus() {
        return status;
    }

    public LocalTime getMomento() {
        return momento;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getMensagem() {
        return mensagem;
    }
}
