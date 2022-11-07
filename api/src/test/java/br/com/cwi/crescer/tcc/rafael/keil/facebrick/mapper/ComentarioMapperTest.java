package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Comentario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.ComentarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarComentarioRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComentarioMapperTest {

    @Test
    public void quandoInformarCriarComentarioRequestEPaiDeveRetornarUmComentario() {

        final CriarComentarioRequest request = ComentarioFixture.requestCompleto();

        final Post post = PostFixture.postCompleto();

        final Comentario comentario = ComentarioMapper.toDomain(request, post);

        assertEquals(request.getAutor(), comentario.getAutor());
        assertEquals(request.getDescricao(), comentario.getDescricao());
        assertEquals(request.getImagem(), comentario.getImagem());
        assertEquals(post, comentario.getPai());
    }
}