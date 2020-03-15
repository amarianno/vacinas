package br.com.vacinas.aws.vacinasawsboot;

public class ErroDeAmbienteException extends RuntimeException {

    public ErroDeAmbienteException(String mensagem) {
        super(mensagem);
    }

    public ErroDeAmbienteException(Class classe, String mensagem) {
        super(classe.getName() + ": " + mensagem);
    }
}
