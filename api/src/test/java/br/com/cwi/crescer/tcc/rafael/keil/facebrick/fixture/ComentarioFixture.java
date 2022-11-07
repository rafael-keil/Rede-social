package br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Comentario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarComentarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarPostRequest;

public class ComentarioFixture {

    public static CriarComentarioRequest requestCompleto() {

        return new CriarComentarioRequest(
                UsuarioFixture.usuarioCompleto(),
                "descricao",
                null
        );
    }

    public static Comentario comentarioCompleto() {

        return new Comentario(
                UsuarioFixture.usuarioCompleto(),
                "descricao",
                null,
                PostFixture.postCompleto()
        );
    }

    public static Comentario comentarioDiferenteCompleto() {

        return new Comentario(
                UsuarioFixture.usuarioCompleto(),
                "descricao2",
                null,
                comentarioCompleto()
        );
    }
}