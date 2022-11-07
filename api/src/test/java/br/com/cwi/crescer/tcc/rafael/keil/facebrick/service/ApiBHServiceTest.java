package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.ValidacaoNegocioException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.UsuarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper.UsuarioBHMapper;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.ApiBHRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioBHRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.RegistrarUsuarioBHRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiBHServiceTest {

    @InjectMocks
    private ApiBHService tested;

    @Mock
    private ApiBHRepository repository;


    @Test
    public void quandoChamarRegistrarUsuarioDeveRegistrarCorretamente() {

        final CriarUsuarioRequest request = UsuarioFixture.requestCompleto();
        final RegistrarUsuarioBHRequest registrarUsuarioBHRequest = UsuarioBHMapper.toRequest(request);

        tested.registrarUsuario(request);

        verify(repository).registrarUsuarioApiBH(registrarUsuarioBHRequest);
    }

    @Test(expected = ValidacaoNegocioException.class)
    public void quandoChamarRegistrarUsuarioInvalidoDeveRetornarException() {

        final CriarUsuarioRequest request = UsuarioFixture.requestCompleto();
        final RegistrarUsuarioBHRequest registrarUsuarioBHRequest = UsuarioBHMapper.toRequest(request);

        doThrow(RuntimeException.class)
                .when(repository).registrarUsuarioApiBH(registrarUsuarioBHRequest);

        tested.registrarUsuario(request);
    }

    @Test
    public void quandoChamarLogarUsuarioDeveLogarUsuario() {

        final LogarUsuarioRequest request = UsuarioFixture.requestLogar();
        final LogarUsuarioBHRequest requestBH = UsuarioBHMapper.toLogin(request);

        when(repository.logarUsuario(requestBH))
                .thenReturn("1111111111111111");

        tested.logarUsuario(request);

        verify(repository).logarUsuario(requestBH);
    }

    @Test(expected = ValidacaoNegocioException.class)
    public void quandoChamarLogarUsuarioInvalidoDeveRetornarException() {

        final LogarUsuarioRequest request = UsuarioFixture.requestLogar();
        final LogarUsuarioBHRequest requestBH = UsuarioBHMapper.toLogin(request);

        doThrow(RuntimeException.class)
                .when(repository).logarUsuario(requestBH);

        tested.logarUsuario(request);
    }
}