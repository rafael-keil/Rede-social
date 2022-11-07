package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Curtida;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.EntradaBase;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;

public class CurtidaMapper {

    public static Curtida toDomain(final Usuario autor, final EntradaBase pai) {

        return new Curtida(
                autor,
                pai
        );
    }
}
