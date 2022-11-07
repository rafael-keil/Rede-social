package br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class NaoPermitidoException extends RuntimeException {
    public NaoPermitidoException(String message) {
        super(message);
    }
}
