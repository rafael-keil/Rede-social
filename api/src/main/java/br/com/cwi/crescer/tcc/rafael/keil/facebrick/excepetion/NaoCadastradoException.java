package br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NaoCadastradoException extends RuntimeException{
    public NaoCadastradoException(String message) {
        super(message);
    }
}
