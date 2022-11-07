package br.com.cwi.crescer.tcc.rafael.keil.facebrick.controller;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.UsuarioPostEmListaResponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.UsuarioResponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/privado/usuario")
public class UsuarioPrivadoController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse usuarioAtual() {

        return service.usuarioAtualResponse();
    }

    @GetMapping("/pesquisar/{pesquisa}")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioPostEmListaResponse> pesquisarUsuario(@PathVariable final String pesquisa) {

        return service.listarPesquisaUsuario(pesquisa);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse pesquisarUsuarioEmail(@PathVariable final String email) {

        return service.procurarUsuarioResponsePorEmail(email);
    }
}
