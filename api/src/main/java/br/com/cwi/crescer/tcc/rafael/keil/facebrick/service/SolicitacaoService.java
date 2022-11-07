package br.com.cwi.crescer.tcc.rafael.keil.facebrick.service;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Solicitacao;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.JaCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.NaoCadastradoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.excepetion.NaoPermitidoException;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper.SolicitacaoMapper;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository.SolicitacaoRepository;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.SolicitacaoResponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.UsuarioPostEmListaResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
public class SolicitacaoService {

    @Autowired
    public SolicitacaoRepository repository;

    @Autowired
    public UsuarioService usuarioService;

    @Autowired
    public UsuarioLogadoService usuarioLogadoService;

    public ModelMapper modelMapper = new ModelMapper();

    public void solicitarAmizade(final String email) {

        final Usuario remetente = usuarioService.usuarioAtual();
        final Usuario destinatario = usuarioService.procurarUsuarioPorEmail(email);

        if (repository.procurarSolicitacaoPorEmail(remetente.getEmail(), destinatario.getEmail()) != null) {
            throw new JaCadastradoException("Já existe solicitação.");
        }

        final Solicitacao solicitacao = SolicitacaoMapper.toDomain(remetente, destinatario);

        repository.save(solicitacao);
    }

    public void aceitarSolicitacao(final String id) {

        final Solicitacao solicitacao = procurarSolicitacaoPorId(id);

        if (!Objects.equals(solicitacao.getDestinatario().getEmail(), usuarioLogadoService.get().getEmail())) {
            throw new NaoPermitidoException("Você não é o destinatário da solicitação.");
        }

        solicitacao.setIsAceita(true);

        repository.save(solicitacao);
    }

    public void deletarSolicitacao(final String id) {

        final Solicitacao solicitacao = procurarSolicitacaoPorId(id);

        repository.delete(solicitacao);
    }

    public Solicitacao procurarSolicitacaoPorId(final String id) {

        return ofNullable(repository.findById(UUID.fromString(id)))
                .orElseThrow(() -> new NaoCadastradoException("Solicitacao não cadastrada"));
    }

    public List<String> procurarEmailAmigosPorEmail(final String email) {

        final List<String> emailAmigos = repository.listarEmailAmigos(email);

        emailAmigos.add(email);

        return emailAmigos;
    }

    public List<String> procurarEmailSolicitacaoPorEmail(final String email) {

        final List<String> emailAmigos = repository.listarEmailSolicitacao(email);

        emailAmigos.add(email);

        return emailAmigos;
    }

    public Page<UsuarioPostEmListaResponse> buscarAmigos(final Pageable pageable) {

        final List<String> emailList = repository.listarEmailAmigos(usuarioLogadoService.get().getEmail());

        return usuarioService.buscarUsuariosPeloEmail(emailList, pageable)
                .map(usuario -> modelMapper.map(usuario, UsuarioPostEmListaResponse.class));
    }

    public Page<SolicitacaoResponse> buscarSolicitacao(final Pageable pageable) {

        return repository.buscarSolicitacaoEmAberto(usuarioLogadoService.get().getEmail(), pageable)
                .map(solicitacao -> modelMapper.map(solicitacao, SolicitacaoResponse.class));
    }
}
