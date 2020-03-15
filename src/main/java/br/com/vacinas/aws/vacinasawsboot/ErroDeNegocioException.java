package br.com.vacinas.aws.vacinasawsboot;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErroDeNegocioException extends RuntimeException {

    private HttpStatus httpStatus;

    public ErroDeNegocioException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
