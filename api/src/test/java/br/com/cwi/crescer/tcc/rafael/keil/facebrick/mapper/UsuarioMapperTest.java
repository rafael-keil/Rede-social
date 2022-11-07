package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.UsuarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UsuarioMapperTest {

    @Test
    public void quandoInformarCriarUsuarioRequestDeveRetornarUmUsuario() {

        final CriarUsuarioRequest request = UsuarioFixture.requestCompleto();

        final Usuario usuario = UsuarioMapper.toDomain(request);

        assertEquals(request.getNome(), usuario.getNome());
        assertEquals(request.getSobrenome(), usuario.getSobrenome());
        assertEquals(request.getEmail(), usuario.getEmail());
        assertEquals(request.getApelido(), usuario.getApelido());
        assertEquals(request.getDataNascimento(), usuario.getDataNascimento());
        assertEquals(request.getImagemPerfil(), usuario.getImagemPerfil());
    }
}