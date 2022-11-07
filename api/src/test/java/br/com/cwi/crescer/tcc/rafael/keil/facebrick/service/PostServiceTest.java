package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.NaoCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.ValidacaoNegocioException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.fixture.UsuarioFixture;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper.PostMapper;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.PostRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarPostRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.PostEmListaResponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.security.UsuarioAutenticado;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @InjectMocks
    private PostService tested;

    @Mock
    private PostRepository repository;

    @Mock
    public UsuarioService usuarioService;

    @Mock
    public SolicitacaoService solicitacaoService;

    @Mock
    public UsuarioLogadoService usuarioLogadoService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void quandoChamarCriarPostDeveCriarCorretamente() {

        final CriarPostRequest request = PostFixture.requestCompleto();
        final Usuario usuario = UsuarioFixture.usuarioCompleto();

        when(usuarioService.usuarioAtual())
                .thenReturn(usuario);

        tested.criarPost(request);

        request.setAutor(usuario);
        final Post post = PostMapper.toDomain(request);

        verify(usuarioService).usuarioAtual();
        verify(repository).save(post);
    }

    @Test
    public void quandoChamarAlterarPrivacidadeDeveInverterPrivacidade() {

        final Post post = PostFixture.postCompleto();

        when(repository.findById(post.getId()))
                .thenReturn(post);

        tested.alterarPrivacidadePost(post.getId().toString());

        post.setIsPrivado(!post.getIsPrivado());

        verify(repository).findById(post.getId());
        verify(repository).save(post);
    }

    @Test
    public void quandoChamarAcharPostPorIdDeveRetornarPost() {

        final Post post = PostFixture.postCompleto();

        when(repository.findById(post.getId()))
                .thenReturn(post);

        tested.acharPostPorId(post.getId().toString());

        verify(repository).findById(post.getId());
    }

    @Test(expected = NaoCadastradoException.class)
    public void quandoChamarAcharPostPorIdInvalidoDeveRetornarException() {

        final Post post = PostFixture.postCompleto();

        when(repository.findById(post.getId()))
                .thenReturn(null);

        tested.acharPostPorId(post.getId().toString());
    }

    @Test
    public void quandoChamarListarPostsDoFeedDeveRetornarPagina() {

        final Pageable pageable = Pageable.ofSize(10);
        final UsuarioAutenticado usuarioAutenticado = UsuarioFixture.usuarioAutenticadoCompleto();
        final List<String> emailList = new ArrayList<>(
                Collections.singletonList(usuarioAutenticado.getEmail())
        );
        final Post post = PostFixture.postCompleto();
        final List<Post> postList = new ArrayList<>(
                Collections.singletonList(post)
        );
        final Page<Post> postPage = new PageImpl<>(postList);
        final Page<PostEmListaResponse> postEmListaResponse =
                postPage.map(postMap -> modelMapper.map(postMap, PostEmListaResponse.class));

        when(usuarioLogadoService.get())
                .thenReturn(usuarioAutenticado);
        when(solicitacaoService.procurarEmailAmigosPorEmail(usuarioAutenticado.getEmail()))
                .thenReturn(emailList);
        when(repository.procurarPostList(emailList, pageable))
                .thenReturn(postPage);

        final Page<PostEmListaResponse> postEmListaResponseRetornado = tested.listarPostsDoFeed(pageable);

        verify(usuarioLogadoService).get();
        verify(solicitacaoService).procurarEmailAmigosPorEmail(usuarioAutenticado.getEmail());
        verify(repository).procurarPostList(emailList, pageable);

        assertEquals(postEmListaResponse, postEmListaResponseRetornado);
    }

    @Test(expected = ValidacaoNegocioException.class)
    public void quandoChamarListarPostsDoFeedGrandeDemaisDeveRetornarException() {

        final Pageable pageable = Pageable.ofSize(50);

        tested.listarPostsDoFeed(pageable);
    }

    @Test
    public void quandoListarPostsPessoaDeveRetornarPage() {

        final Usuario usuario = UsuarioFixture.usuarioCompleto();
        final UsuarioAutenticado usuarioAutenticado = UsuarioFixture.usuarioAutenticadoCompleto();
        final Pageable pageable = PageRequest.of(0, 10);
        final Post post = PostFixture.postCompleto();
        final List<Post> postList = new ArrayList<>(Collections.singletonList(post));
        final Page<Post> postPage = new PageImpl<>(postList, pageable, postList.size());

        when(usuarioLogadoService.get())
                .thenReturn(usuarioAutenticado);
        when(repository.procurarPostPessoa(usuario.getEmail(), false, pageable))
                .thenReturn(postPage);

        final Page<PostEmListaResponse> postEmListaResponses = tested.listarPostsPessoa(usuario.getEmail(), pageable);

        verify(usuarioLogadoService).get();
        verify(repository).procurarPostPessoa(usuario.getEmail(), false, pageable);
    }
}