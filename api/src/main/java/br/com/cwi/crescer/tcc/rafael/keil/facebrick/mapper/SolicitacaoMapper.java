package br.com.cwi.crescer.tcc.rafael.keil.facebrick.mapper;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Solicitacao;
import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;

public class SolicitacaoMapper {

    public static Solicitacao toDomain(final Usuario remetente, final Usuario destinatario) {

        return new Solicitacao(
                remetente,
                destinatario
        );
    }
}
