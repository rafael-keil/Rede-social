package br.com.cwi.crescer.tcc.rafael.keil.facebrick.controller;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.service.CurtidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/privado/curtir")
public class CurtidaPrivadoController {

    @Autowired
    private CurtidaService service;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void curtirPost(@PathVariable final String id) {

        service.curtirEntrada(id);
    }
}
