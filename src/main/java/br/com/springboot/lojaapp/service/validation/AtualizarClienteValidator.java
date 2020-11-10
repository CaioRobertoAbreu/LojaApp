package br.com.springboot.lojaapp.service.validation;

import br.com.springboot.lojaapp.controller.exception.CampoComErro;
import br.com.springboot.lojaapp.dto.ClienteDto;
import br.com.springboot.lojaapp.model.Cliente;
import br.com.springboot.lojaapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AtualizarClienteValidator implements ConstraintValidator<AtualizarCliente, ClienteDto> {

    @Autowired
    private final ClienteRepository clienteRepository;

    private final HttpServletRequest httpServletRequest;

    public AtualizarClienteValidator(ClienteRepository clienteRepository, HttpServletRequest httpServletRequest) {
        this.clienteRepository = clienteRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void initialize(AtualizarCliente constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClienteDto cliente, ConstraintValidatorContext context) {
        List<CampoComErro> erros = new ArrayList<>();

        @SuppressWarnings("unchecked")
        Map<String, String> requisicao = (Map<String, String>) httpServletRequest
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Integer id = Integer.parseInt(requisicao.get("id"));

        Cliente clienteEmail = clienteRepository.findByEmail(cliente.getEmail());

        if(clienteEmail != null && !clienteEmail.getId().equals(id)) {
            erros.add(new CampoComErro("email", "Email já existente"));
        }

        for (CampoComErro e : erros) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getCampo())
                    .addConstraintViolation();
        }

        return erros.isEmpty();
    }
}
