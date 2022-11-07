package br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JaCadastradoException extends RuntimeException{
    public JaCadastradoException(String message) {
        super(message);
    }
}
