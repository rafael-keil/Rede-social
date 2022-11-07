package br.com.cwi.crescer.tcc.rafael.keil.facebrick.controller;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarPostRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response.PostEmListaResponse;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/privado/post")
public class PostPrivadoController {

    @Autowired
    private PostService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarPost(@RequestBody @Valid final CriarPostRequest request) {

        service.criarPost(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void alterarPrivacidadePost(@PathVariable final String id) {

        service.alterarPrivacidadePost(id);
    }

    @GetMapping
    public Page<PostEmListaResponse> listarPostsDoFeed(final Pageable pageable) {

        return service.listarPostsDoFeed(pageable);
    }

    @GetMapping("/{email}")
    public Page<PostEmListaResponse> listarPostsPessoa(@PathVariable final String email, final Pageable pageable) {

        return service.listarPostsPessoa(email, pageable);
    }
}
