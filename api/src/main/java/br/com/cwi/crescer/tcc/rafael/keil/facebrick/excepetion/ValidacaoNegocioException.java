package br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidacaoNegocioException extends RuntimeException {
    public ValidacaoNegocioException(String message) {
        super(message);
    }
}
