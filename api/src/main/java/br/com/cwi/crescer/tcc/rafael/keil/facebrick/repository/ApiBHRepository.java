package br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioBHRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.RegistrarUsuarioBHRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient(url = "https://crescer-api-auth.herokuapp.com", name = "ApiBH")
public interface ApiBHRepository {

    @PostMapping("/register")
    void registrarUsuarioApiBH(@RequestBody RegistrarUsuarioBHRequest request);


    @PostMapping("/login")
    String logarUsuario(@RequestBody LogarUsuarioBHRequest request);
}