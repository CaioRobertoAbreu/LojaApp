package br.com.springboot.lojaapp.model;

import br.com.springboot.lojaapp.model.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    private LocalDate dataPagamento;
    private LocalDate dataVencimento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,
                              LocalDate dataVencimento, LocalDate dataPagamento) {

        super(id, estado, pedido);
        this.dataPagamento = dataPagamento;

        if(dataVencimento.getDayOfWeek().getValue() == 6) {
            this.dataVencimento = dataVencimento.plusDays(2);

        } else if(dataVencimento.getDayOfWeek().getValue() == 7) {
            this.dataVencimento = dataVencimento.plusDays(1);

        } else {
            this.dataVencimento = dataVencimento;
        }


    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }


}
