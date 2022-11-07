package br.com.cwi.crescer.tcc.rafael.keil.facebrick.controller;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/publico/usuario")
public class UsuarioPublicoController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUsuario(@RequestBody @Valid final CriarUsuarioRequest request) {

        service.criarUsuario(request);
    }

    @PostMapping("/logar")
    @ResponseStatus(HttpStatus.OK)
    public String logarUsuario(@RequestBody @Valid final LogarUsuarioRequest request) {

        return service.logarUsuario(request);
    }
}
