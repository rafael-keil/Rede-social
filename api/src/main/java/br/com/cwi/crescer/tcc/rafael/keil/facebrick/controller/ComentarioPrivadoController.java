package br.com.cwi.crescer.tcc.rafael.keil.facebrick.controller;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarComentarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.ComentarioPostEmListaReponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/privado/comentar")
public class ComentarioPrivadoController {

    @Autowired
    private ComentarioService service;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarComentario(@RequestBody @Valid final CriarComentarioRequest request, @PathVariable final String id) {

        service.criarComentario(request, id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ComentarioPostEmListaReponse> listarComentarios(@PathVariable final String id) {

        return service.listarComentarios(id);
    }
}
