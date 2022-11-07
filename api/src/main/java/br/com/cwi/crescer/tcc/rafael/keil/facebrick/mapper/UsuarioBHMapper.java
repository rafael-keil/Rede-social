package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioBHRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.LogarUsuarioRequest;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.RegistrarUsuarioBHRequest;

public class UsuarioBHMapper {

    public static RegistrarUsuarioBHRequest toRequest(final CriarUsuarioRequest request) {

        return new RegistrarUsuarioBHRequest(
                request.getEmail(),
                request.getNome(),
                request.getSobrenome(),
                request.getSenha()
        );
    }

    public static LogarUsuarioBHRequest toLogin(final LogarUsuarioRequest request) {

        return new LogarUsuarioBHRequest(
                request.getEmail(),
                request.getSenha()
        );
    }
}
