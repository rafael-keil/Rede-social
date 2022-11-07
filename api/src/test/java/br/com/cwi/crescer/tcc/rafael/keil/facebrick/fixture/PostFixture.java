package br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarPostRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioRequest;

import java.time.LocalDate;

public class PostFixture {

    public static CriarPostRequest requestCompleto() {

        return new CriarPostRequest(
                UsuarioFixture.usuarioCompleto(),
                "descricao",
                null,
                "titulo",
                29.5,
                true
        );
    }

    public static Post postCompleto() {

        return new Post(
                UsuarioFixture.usuarioCompleto(),
                "descricao",
                null,
                "titulo",
                29.5,
                true
        );
    }

}
