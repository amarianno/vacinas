package br.com.vacinas.aws.vacinasawsboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Mensagem> handleConflict(RuntimeException ex, WebRequest request) {
        return Mensagem.builder().mensagem("Deu erro " + ex.getMessage()).build();
    }

}
