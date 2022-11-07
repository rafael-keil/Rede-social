package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Comentario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.NaoCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.ComentarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.UsuarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.ComentarioRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarComentarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.ComentarioPostEmListaReponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComentarioServiceTest {

    @InjectMocks
    private ComentarioService tested;

    @Mock
    private ComentarioRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private PostService postService;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void quandoChamarCriarComentarioDePostDeveCriarCorretamente() {

        final CriarComentarioRequest request = ComentarioFixture.requestCompleto();
        final Comentario comentario = ComentarioFixture.comentarioCompleto();
        final Post post = PostFixture.postCompleto();
        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final String id = post.getId().toString();

        when(usuarioService.usuarioAtual())
                .thenReturn(usuario);
        when(repository.findById(post.getId()))
                .thenReturn(null);
        when(postService.acharPostPorId(id))
                .thenReturn(post);

        tested.criarComentario(request, id);

        verify(usuarioService).usuarioAtual();
        verify(postService).acharPostPorId(id);
        verify(repository).save(comentario);
    }

    @Test
    public void quandoChamarCriarComentarioDeComentarioDeveCriarCorretamente() {

        final CriarComentarioRequest request = ComentarioFixture.requestCompleto();
        final Comentario comentarioDiferente = ComentarioFixture.comentarioDiferenteCompleto();
        final Comentario comentario = ComentarioFixture.comentarioCompleto();
        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final String id = comentario.getId().toString();

        when(usuarioService.usuarioAtual())
                .thenReturn(usuario);
        when(repository.findById(comentario.getId()))
                .thenReturn(comentario);

        tested.criarComentario(request, id);

        verify(usuarioService).usuarioAtual();
        verify(repository).save(comentarioDiferente);
    }

    @Test
    public void quandoChamarAcharComentarioPorIdDeveRetornarComentario() {

        final Comentario comentario = ComentarioFixture.comentarioCompleto();

        when(repository.findById(comentario.getId()))
                .thenReturn(comentario);

        tested.acharComentarioPorId(comentario.getId().toString());

        verify(repository).findById(comentario.getId());
    }

    @Test(expected = NaoCadastradoException.class)
    public void quandoChamarAcharComentarioPorIdInvalidoDeveRetornarException() {

        final Comentario comentario = ComentarioFixture.comentarioCompleto();

        when(repository.findById(comentario.getId()))
                .thenReturn(null);

        tested.acharComentarioPorId(comentario.getId().toString());
    }

    @Test
    public void quandoChamarListarComentariosDeveRetornarComentarioPostEmListaResponse() {

        final Comentario comentario = ComentarioFixture.comentarioCompleto();
        final Comentario comentarioDiferente = ComentarioFixture.comentarioDiferenteCompleto();

        final List<Comentario> comentarioList = new ArrayList<>(Arrays.asList(comentario, comentarioDiferente));

        final List<ComentarioPostEmListaReponse> responseList = comentarioList
                .stream()
                .map(comentarioItem -> modelMapper.map(comentarioItem, ComentarioPostEmListaReponse.class))
                .collect(Collectors.toList());

        when(repository.findByPaiId(comentario.getPai().getId()))
                .thenReturn(comentarioList);

        final List<ComentarioPostEmListaReponse> comentarioPostEmListaReponse = tested.listarComentarios(comentario.getPai().getId().toString());

        verify(repository).findByPaiId((comentario.getPai().getId()));

        assertEquals(responseList, comentarioPostEmListaReponse);
    }
}