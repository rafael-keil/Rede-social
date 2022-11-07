package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoResponse {

    private String id;

    private UsuarioPostEmListaResponse remetente;

    private UsuarioPostEmListaResponse destinatario;

    private Boolean isAceita;
}
