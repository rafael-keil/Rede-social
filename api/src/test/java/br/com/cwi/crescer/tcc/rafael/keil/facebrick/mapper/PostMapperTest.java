package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarPostRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostMapperTest {

    @Test
    public void quandoInformarCriarPostRequestDeveRetornarUmPost() {

        final CriarPostRequest request = PostFixture.requestCompleto();

        final Post post = PostMapper.toDomain(request);

        assertEquals(request.getAutor(), post.getAutor());
        assertEquals(request.getDescricao(), post.getDescricao());
        assertEquals(request.getImagem(), post.getImagem());
        assertEquals(request.getTitulo(), post.getTitulo());
        assertEquals(request.getValor(), post.getValor());
        assertEquals(request.getIsPrivado(), post.getIsPrivado());
    }
}