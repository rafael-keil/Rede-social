package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.JaCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.NaoCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.UsuarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.UsuarioRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.UsuarioResponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.security.UsuarioAutenticado;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService tested;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private ApiBHService apiBHService;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void quandoChamarCriarUsuarioDeveCriarCorretamente() {

        final CriarUsuarioRequest request = UsuarioFixture.requestCompleto();
        final Usuario usuario = UsuarioFixture.usuarioCompleto();

        when(repository.findByEmail(request.getEmail()))
                .thenReturn(null);

        tested.criarUsuario(request);

        verify(repository).findByEmail(request.getEmail());
        verify(apiBHService).registrarUsuario(request);
        verify(repository).save(usuario);
    }

    @Test(expected = JaCadastradoException.class)
    public void quandoChamarCriarUsuarioJaCadastradoDeveRetornarException() {

        final CriarUsuarioRequest request = UsuarioFixture.requestCompleto();
        final Usuario usuario = UsuarioFixture.usuarioCompleto();

        when(repository.findByEmail(request.getEmail()))
                .thenReturn(usuario);

        tested.criarUsuario(request);
    }

    @Test
    public void quandoChamarLogarUsuarioDeveLogarCorretamente() {

        final LogarUsuarioRequest request = UsuarioFixture.requestLogar();

        when(apiBHService.logarUsuario(request))
                .thenReturn(null);

        tested.logarUsuario(request);

        verify(apiBHService).logarUsuario(request);

    }

    @Test
    public void quandoProcurarUsuarioPorEmailDeveRetornarUsuario() {

        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final String email = usuario.getEmail();

        when(repository.findByEmail(email))
                .thenReturn(usuario);

        tested.procurarUsuarioPorEmail(email);

        verify(repository).findByEmail(email);
    }

    @Test(expected = NaoCadastradoException.class)
    public void quandoProcurarUsuarioPorEmailInvalidoDeveRetornarexception() {

        final String email = "email inexistente";

        when(repository.findByEmail(email))
                .thenReturn(null);

        tested.procurarUsuarioPorEmail(email);
    }

    @Test
    public void quandoChamarUsuarioAtualDeveRetornarUsuario() {

        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final UsuarioAutenticado usuarioAutenticado = UsuarioFixture.usuarioAutenticadoCompleto();

        when(usuarioLogadoService.get())
                .thenReturn(usuarioAutenticado);
        when(repository.findByEmail(usuarioAutenticado.getEmail()))
                .thenReturn(usuario);

        final Usuario usuarioRetornado = tested.usuarioAtual();

        verify(usuarioLogadoService).get();
        verify(repository).findByEmail(usuarioAutenticado.getEmail());

        assertEquals(usuario, usuarioRetornado);
    }

    @Test
    public void quandoChamarUsuarioAtualResponseDeveRetornarUsuarioResponse() {

        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final UsuarioResponse usuarioResponse = modelMapper.map(usuario, UsuarioResponse.class);
        final UsuarioAutenticado usuarioAutenticado = UsuarioFixture.usuarioAutenticadoCompleto();

        when(usuarioLogadoService.get())
                .thenReturn(usuarioAutenticado);
        when(repository.findByEmail(usuarioAutenticado.getEmail()))
                .thenReturn(usuario);

        final UsuarioResponse usuarioResponseRetornado = tested.usuarioAtualResponse();

        verify(usuarioLogadoService).get();
        verify(repository).findByEmail(usuarioAutenticado.getEmail());

        assertEquals(usuarioResponse, usuarioResponseRetornado);
    }
}