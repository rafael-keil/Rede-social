package br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Curtida;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarPostRequest;

public class CurtidaFixture {

    public static Curtida curtidaPostCompleto() {

        return new Curtida(
                UsuarioFixture.usuarioCompleto(),
                PostFixture.postCompleto()
        );
    }

    public static Curtida curtidaComentarioCompleto() {

        return new Curtida(
                UsuarioFixture.usuarioCompleto(),
                ComentarioFixture.comentarioCompleto()
        );
    }

}
