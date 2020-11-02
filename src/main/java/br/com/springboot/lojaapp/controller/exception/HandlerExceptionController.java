package br.com.springboot.lojaapp.controller.exception;

import br.com.springboot.lojaapp.service.exception.DataIntegrityException;
import br.com.springboot.lojaapp.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErroPadrao> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(HttpStatus.NOT_FOUND.value(), LocalTime.now(),
                System.currentTimeMillis(), e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ErroPadrao> dataIntegretyException(DataIntegrityException exception,
                                                             HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), LocalTime.now(),
                System.currentTimeMillis(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> validation(MethodArgumentNotValidException exception,
                                                                      HttpServletRequest request) {

        ErroValidacao erro = new ErroValidacao(HttpStatus.BAD_REQUEST.value(), LocalTime.now(),
                System.currentTimeMillis(), exception.getMessage());

        exception.getBindingResult().getFieldErrors().forEach(fieldError ->
                erro.addErro(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
