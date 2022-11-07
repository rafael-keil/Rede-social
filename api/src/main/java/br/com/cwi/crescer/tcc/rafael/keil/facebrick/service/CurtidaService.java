package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Comentario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Curtida;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper.CurtidaMapper;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CurtidaService {

    @Autowired
    private CurtidaRepository repository;

    @Autowired
    public UsuarioService usuarioService;

    @Autowired
    public PostService postService;

    @Autowired
    public ComentarioService comentarioService;

    @Autowired
    public UsuarioLogadoService usuarioLogadoService;

    public void curtirEntrada(final String id) {

        final String email = usuarioLogadoService.get().getEmail();

        final Curtida curtidaRepository = repository.findByPaiIdAndAutorEmail(UUID.fromString(id), email);

        if (curtidaRepository == null) {

            final Usuario usuario = usuarioService.usuarioAtual();

            try {

                final Comentario comentario = comentarioService.acharComentarioPorId(id);

                final Curtida curtida = CurtidaMapper.toDomain(usuario, comentario);

                repository.save(curtida);

            } catch (final RuntimeException exception) {

                final Post post = postService.acharPostPorId(id);

                final Curtida curtida = CurtidaMapper.toDomain(usuario, post);

                repository.save(curtida);
            }
        } else {
            repository.delete(curtidaRepository);
        }
    }
}
