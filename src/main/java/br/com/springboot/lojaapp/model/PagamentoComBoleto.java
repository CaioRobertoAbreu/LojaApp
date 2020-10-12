package br.com.springboot.lojaapp.model;

import br.com.springboot.lojaapp.model.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,
                              LocalDate dataVencimento, LocalDate dataPagamento) {

        super(id, estado, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = alteraDataVencimentoSeFimDeSemana(dataVencimento);
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

    private LocalDate alteraDataVencimentoSeFimDeSemana(LocalDate vencimento) {

        if(vencimento.getDayOfWeek().getValue() < 6) {
            return vencimento;

        } else if(vencimento.getDayOfWeek().getValue() == 6) {
            return vencimento.plusDays(2);

        } else {
            return  vencimento.plusDays(1);
        }
    }

}
