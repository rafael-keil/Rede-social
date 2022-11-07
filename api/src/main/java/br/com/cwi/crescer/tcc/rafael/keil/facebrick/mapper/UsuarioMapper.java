package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarUsuarioRequest;

public class UsuarioMapper {

    public static Usuario toDomain(final CriarUsuarioRequest request) {

        return new Usuario(
                request.getNome(),
                request.getSobrenome(),
                request.getEmail(),
                request.getApelido(),
                request.getDataNascimento(),
                request.getImagemPerfil()
        );
    }
}
