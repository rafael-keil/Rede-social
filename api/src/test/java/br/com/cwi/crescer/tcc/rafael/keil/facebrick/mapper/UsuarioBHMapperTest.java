package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.UsuarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioBHRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.RegistrarUsuarioBHRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UsuarioBHMapperTest {

    @Test
    public void quandoInformarCriarUsuarioRequestDeveRetornarUmRegistrarUsuarioBHRequest() {

        final CriarUsuarioRequest request = UsuarioFixture.requestCompleto();

        final RegistrarUsuarioBHRequest usuario = UsuarioBHMapper.toRequest(request);

        assertEquals(request.getNome(), usuario.getFirstName());
        assertEquals(request.getSobrenome(), usuario.getLastName());
        assertEquals(request.getEmail(), usuario.getEmail());
        assertEquals(request.getSenha(), usuario.getPassword());
    }

    @Test
    public void quandoInformarLogarUsuarioRequesDeveRetornarUmLogarUsuarioBHRequest() {

        final LogarUsuarioRequest request = UsuarioFixture.requestLogar();

        final LogarUsuarioBHRequest requestBH = UsuarioBHMapper.toLogin(request);

        assertEquals(request.getEmail(), requestBH.getEmail());
        assertEquals(request.getSenha(), requestBH.getPassword());
    }
}