package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Comentario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.NaoCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper.ComentarioMapper;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.ComentarioRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarComentarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.ComentarioPostEmListaReponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostService postService;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    private final ModelMapper modelMapper = new ModelMapper();

    public void criarComentario(final CriarComentarioRequest request, final String id) {

        final Usuario usuario = usuarioService.usuarioAtual();

        request.setAutor(usuario);

        try {

            final Comentario comentarioEncontrado = acharComentarioPorId(id);

            final Comentario comentario = ComentarioMapper.toDomain(request, comentarioEncontrado);

            repository.save(comentario);

        } catch (final RuntimeException exception) {

            final Post postEncontrado = postService.acharPostPorId(id);

            final Comentario comentario = ComentarioMapper.toDomain(request, postEncontrado);

            repository.save(comentario);
        }
    }

    public List<ComentarioPostEmListaReponse> listarComentarios(final String id) {

        return repository.findByPaiId(UUID.fromString(id))
                .stream()
                .map(comentario -> modelMapper.map(comentario, ComentarioPostEmListaReponse.class))
                .collect(Collectors.toList());
    }

    public Comentario acharComentarioPorId(final String id) {

        return ofNullable(repository.findById(UUID.fromString(id)))
                .orElseThrow(() -> new NaoCadastradoException("Curtida não cadastrada"));
    }
}
