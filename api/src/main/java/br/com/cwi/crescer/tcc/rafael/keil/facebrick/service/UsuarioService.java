package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.JaCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.NaoCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper.UsuarioMapper;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.UsuarioRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.UsuarioPostEmListaResponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.UsuarioResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ApiBHService apiBHService;

    @Autowired
    private SolicitacaoService solicitacaoService;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    ModelMapper modelMapper = new ModelMapper();

    public void criarUsuario(final CriarUsuarioRequest request) {

        final Usuario usuario = UsuarioMapper.toDomain(request);

        if (repository.findByEmail(request.getEmail()) != null) {
            throw new JaCadastradoException("Email já cadastrado.");
        }

        apiBHService.registrarUsuario(request);

        repository.save(usuario);
    }

    public String logarUsuario(final LogarUsuarioRequest request) {

        return apiBHService.logarUsuario(request);
    }

    public List<UsuarioPostEmListaResponse> listarPesquisaUsuario(final String pesquisa) {

        final List<String> emailList = solicitacaoService.procurarEmailSolicitacaoPorEmail(usuarioLogadoService.get().getEmail());

        return repository.listarPesquisaUsuario(pesquisa, emailList)
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioPostEmListaResponse.class))
                .collect(Collectors.toList());
    }

    public UsuarioResponse usuarioAtualResponse() {

        return modelMapper.map(usuarioAtual(), UsuarioResponse.class);
    }

    public Usuario usuarioAtual() {

        return procurarUsuarioPorEmail(usuarioLogadoService.get().getEmail());
    }

    public UsuarioResponse procurarUsuarioResponsePorEmail(final String email) {

        return modelMapper.map(procurarUsuarioPorEmail(email), UsuarioResponse.class);
    }

    public Usuario procurarUsuarioPorEmail(final String email) {

        return ofNullable(repository.findByEmail(email))
                .orElseThrow(() -> new NaoCadastradoException("Usuário não cadastrado."));
    }

    public Page<Usuario> buscarUsuariosPeloEmail(final List<String> emailList, final Pageable pageable) {

        return repository.findAllByEmailIsIn(emailList, pageable);
    }
}
