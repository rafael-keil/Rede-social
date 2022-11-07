package br.com.cwi.crescer.tcc.rafael.keil.facebrick.controller;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.SolicitacaoResponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.UsuarioPostEmListaResponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/privado/solicitacao")
public class SolicitacaoPrivadoController {

    @Autowired
    private SolicitacaoService service;

    @PostMapping("/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public void solicitarAmizade(@PathVariable final String email) {

        service.solicitarAmizade(email);
    }

    @PutMapping("/aceitar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void aceitarSolicitacao(@PathVariable final String id) {

        service.aceitarSolicitacao(id);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarSolicitacao(@PathVariable final String id) {

        service.deletarSolicitacao(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioPostEmListaResponse> buscarAmigos(final Pageable pageable) {

        return service.buscarAmigos(pageable);
    }

    @GetMapping("/pendente")
    @ResponseStatus(HttpStatus.OK)
    public Page<SolicitacaoResponse> buscarSolicitacao(final Pageable pageable) {

        return service.buscarSolicitacao(pageable);
    }
}
