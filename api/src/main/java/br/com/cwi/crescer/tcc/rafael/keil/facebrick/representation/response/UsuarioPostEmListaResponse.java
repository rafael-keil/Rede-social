package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostEmListaResponse {

    private String nome;

    private String sobrenome;

    private String imagemPerfil;

    private String email;
}
