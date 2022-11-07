package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.NaoCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.ValidacaoNegocioException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper.PostMapper;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.PostRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarPostRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.PostEmListaResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
public class PostService {

    @Autowired
    public PostRepository repository;

    @Autowired
    public UsuarioService usuarioService;

    @Autowired
    public SolicitacaoService solicitacaoService;

    @Autowired
    public UsuarioLogadoService usuarioLogadoService;

    private final ModelMapper modelMapper = new ModelMapper();

    public void criarPost(final CriarPostRequest request) {

        final Usuario usuario = usuarioService.usuarioAtual();

        request.setAutor(usuario);

        final Post post = PostMapper.toDomain(request);

        repository.save(post);
    }

    public void alterarPrivacidadePost(final String id) {

        final Post post = acharPostPorId(id);

        post.setIsPrivado(!post.getIsPrivado());

        repository.save(post);
    }

    public Page<PostEmListaResponse> listarPostsDoFeed(final Pageable pageable) {

        if (pageable.getPageSize() > 20) {
            throw new ValidacaoNegocioException("Página muito grande");
        }

        final List<String> emailList = solicitacaoService.procurarEmailAmigosPorEmail(usuarioLogadoService.get().getEmail());

        return repository.procurarPostList(emailList, pageable)
                .map(post -> modelMapper.map(post, PostEmListaResponse.class));
    }

    public Post acharPostPorId(final String id) {

        return ofNullable(repository.findById(UUID.fromString(id)))
                .orElseThrow(() -> new NaoCadastradoException("Motorista não cadastrado"));
    }

    public Page<PostEmListaResponse> listarPostsPessoa(final String email, final Pageable pageable) {

        final List<String> emailList = solicitacaoService.procurarEmailAmigosPorEmail(usuarioLogadoService.get().getEmail());

        return repository.procurarPostPessoa(email, emailList.contains(email), pageable)
                .map(post -> modelMapper.map(post, PostEmListaResponse.class));
    }
}
