package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEmListaResponse {

    private UUID id;

    private UsuarioPostEmListaResponse autor;

    private LocalDateTime data;

    private String titulo;

    private Double valor;

    private Boolean isPrivado;

    private String descricao;

    private String imagem;

    private List<CurtidaPostEmListaResponse> curtida = new ArrayList<>();

}
