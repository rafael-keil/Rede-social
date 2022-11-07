package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogarUsuarioRequest {

    @Email
    private String email;

    @NotEmpty
    private String senha;
}
