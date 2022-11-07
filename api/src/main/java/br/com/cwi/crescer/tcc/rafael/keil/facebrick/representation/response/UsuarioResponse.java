package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Solicitacao;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioResponse {

    private String nome;

    private String sobrenome;

    private String email;

    private String apelido;

    private LocalDate dataNascimento;

    private String imagemPerfil;

    private List<Solicitacao> solicitacao = new ArrayList<>();
}
