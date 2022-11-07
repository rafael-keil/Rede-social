package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Comentario;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.EntradaBase;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request.CriarComentarioRequest;

public class ComentarioMapper {

    public static Comentario toDomain(final CriarComentarioRequest request, final EntradaBase pai) {

        return new Comentario(
                request.getAutor(),
                request.getDescricao(),
                request.getImagem(),
                pai
        );
    }
}