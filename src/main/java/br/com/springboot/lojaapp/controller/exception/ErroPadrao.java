package br.com.springboot.lojaapp.controller.exception;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ErroPadrao implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String momento;
    private Long timeStamp;
    private String mensagem;

    public ErroPadrao(Integer status, LocalTime momento, Long timeStamp, String mensagem) {
        this.status = status;
        this.momento = momento.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.timeStamp = timeStamp;
        this.mensagem = mensagem;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMomento() {
        return momento;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getMensagem() {
        return mensagem;
    }
}
