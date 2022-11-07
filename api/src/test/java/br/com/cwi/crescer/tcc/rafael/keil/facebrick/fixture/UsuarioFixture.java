package br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.security.UsuarioAutenticado;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.security.bh.UserResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioFixture {

    public static CriarUsuarioRequest requestCompleto() {

        return new CriarUsuarioRequest(
                "nome",
                "sobrenome",
                "email@email.com",
                "apelido",
                LocalDate.of(2001, 11, 29),
                "SenhaForte123",
                "imagem.png"
        );
    }

    public static Usuario usuarioCompleto() {

        return new Usuario(
                "nome",
                "sobrenome",
                "email@email.com",
                "apelido",
                LocalDate.of(2001, 11, 29),
                "imagem.png"
        );
    }

    public static Usuario usuarioDiferente() {
        return new Usuario(
                "nome2",
                "sobrenome2",
                "email2@email.com",
                "apelido2",
                LocalDate.of(2001, 11, 29),
                "imagem2.png"
        );
    }

    public static LogarUsuarioRequest requestLogar() {

        return new LogarUsuarioRequest(
                "rafael@gmail.com",
                "RafaelSenha123"
        );
    }

    public static UsuarioAutenticado usuarioAutenticadoCompleto() {

        return new UsuarioAutenticado(
                "rafael",
                "keil",
                "email@email.com",
                null
        );
    }
}
