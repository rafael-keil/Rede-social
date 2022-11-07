package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarPostRequest;

public class PostMapper {

    public static Post toDomain(final CriarPostRequest request) {

        return new Post(
                request.getAutor(),
                request.getDescricao(),
                request.getImagem(),
                request.getTitulo(),
                request.getValor(),
                request.getIsPrivado()
        );
    }
}
