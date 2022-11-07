package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogarUsuarioBHRequest {

    @Email
    private String email;

    @NotEmpty
    private String password;
}
