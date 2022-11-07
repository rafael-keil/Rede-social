package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarUsuarioRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    @Email
    private String email;

    private String apelido;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotEmpty
    private String senha;

    private String imagemPerfil;
}
