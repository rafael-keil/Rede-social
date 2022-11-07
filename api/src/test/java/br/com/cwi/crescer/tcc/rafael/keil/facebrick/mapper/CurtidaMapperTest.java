package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Curtida;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.UsuarioFixture;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurtidaMapperTest {

    @Test
    public void quandoInformarAutorEPaiDeveRetornarCurtida() {

        final Usuario usuario = UsuarioFixture.usuarioCompleto();

        final Post post = PostFixture.postCompleto();

        final Curtida curtida = CurtidaMapper.toDomain(usuario, post);

        assertEquals(usuario, curtida.getAutor());
        assertEquals(post, curtida.getPai());
    }
}