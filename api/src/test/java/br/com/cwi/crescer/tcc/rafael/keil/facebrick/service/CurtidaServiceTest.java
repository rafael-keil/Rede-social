package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Comentario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Curtida;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.ComentarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.CurtidaFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.UsuarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.CurtidaRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.security.UsuarioAutenticado;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CurtidaServiceTest {

    @InjectMocks
    private CurtidaService tested;

    @Mock
    private CurtidaRepository repository;

    @Mock
    public UsuarioService usuarioService;

    @Mock
    private PostService postService;

    @Mock
    public ComentarioService comentarioService;

    @Mock
    public UsuarioLogadoService usuarioLogadoService;

    @Test
    public void quandoChamarCurtirPostDeveCurtirCorretamente() {

        final Curtida curtida = CurtidaFixture.curtidaPostCompleto();
        final Post post = PostFixture.postCompleto();
        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final UsuarioAutenticado usuarioAutenticado = UsuarioFixture.usuarioAutenticadoCompleto();
        final String id = post.getId().toString();

        when(usuarioLogadoService.get())
                .thenReturn(usuarioAutenticado);
        when(repository.findByPaiIdAndAutorEmail(post.getId(), usuario.getEmail()))
                .thenReturn(null);
        when(usuarioService.usuarioAtual())
                .thenReturn(usuario);
        doThrow(RuntimeException.class).
                when(comentarioService).acharComentarioPorId(id);
        when(postService.acharPostPorId(id))
                .thenReturn(post);

        tested.curtirEntrada(id);

        verify(usuarioLogadoService).get();
        verify(repository).findByPaiIdAndAutorEmail(post.getId(), usuario.getEmail());
        verify(usuarioService).usuarioAtual();
        verify(comentarioService).acharComentarioPorId(id);
        verify(postService).acharPostPorId(id);
        verify(repository).save(curtida);
    }

    @Test
    public void quandoChamarCurtirComentarioDeveCurtirCorretamente() {

        final Curtida curtida = CurtidaFixture.curtidaComentarioCompleto();
        final Comentario comentario = ComentarioFixture.comentarioCompleto();
        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final UsuarioAutenticado usuarioAutenticado = UsuarioFixture.usuarioAutenticadoCompleto();
        final String id = comentario.getId().toString();

        when(usuarioLogadoService.get())
                .thenReturn(usuarioAutenticado);
        when(repository.findByPaiIdAndAutorEmail(comentario.getId(), usuario.getEmail()))
                .thenReturn(null);
        when(usuarioService.usuarioAtual())
                .thenReturn(usuario);
        when(comentarioService.acharComentarioPorId(id))
                .thenReturn(comentario);

        tested.curtirEntrada(id);

        verify(usuarioLogadoService).get();
        verify(repository).findByPaiIdAndAutorEmail(comentario.getId(), usuario.getEmail());
        verify(usuarioService).usuarioAtual();
        verify(comentarioService).acharComentarioPorId(id);
        verify(repository).save(curtida);
    }

    @Test
    public void quandoChamarCurtirEntradaJaExistenteDeveDeletarCurtida() {

        final Curtida curtida = CurtidaFixture.curtidaPostCompleto();
        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final UsuarioAutenticado usuarioAutenticado = UsuarioFixture.usuarioAutenticadoCompleto();

        when(repository.findByPaiIdAndAutorEmail(curtida.getPai().getId(), usuario.getEmail()))
                .thenReturn(curtida);
        when(usuarioLogadoService.get())
                .thenReturn(usuarioAutenticado);

        tested.curtirEntrada(curtida.getPai().getId().toString());

        verify(repository).delete(curtida);
    }
}