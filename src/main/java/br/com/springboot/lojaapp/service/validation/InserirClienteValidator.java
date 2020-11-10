package br.com.springboot.lojaapp.service.validation;

import br.com.springboot.lojaapp.controller.exception.CampoComErro;
import br.com.springboot.lojaapp.dto.ClienteNewDto;
import br.com.springboot.lojaapp.model.enums.TipoCliente;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class InserirClienteValidator implements ConstraintValidator<InserirCliente, ClienteNewDto> {

    @Override
    public void initialize(InserirCliente constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClienteNewDto cliente, ConstraintValidatorContext context) {

        List<CampoComErro> erros = new ArrayList<>();

        if(cliente.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo()) &&
        !ValidaCpfCnpj.isValidCPF(cliente.getCpf_Cnpj())) {

            erros.add(new CampoComErro("cpf_Cnpj", "CPF invalido"));
        }

        if(cliente.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) &&
                !ValidaCpfCnpj.isValidCNPJ(cliente.getCpf_Cnpj())) {

            erros.add(new CampoComErro("cpf_Cnpj", "CNPJ invalido"));
        }

        for (CampoComErro e : erros) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getCampo())
                    .addConstraintViolation();
        }

        return erros.isEmpty();
    }
}


