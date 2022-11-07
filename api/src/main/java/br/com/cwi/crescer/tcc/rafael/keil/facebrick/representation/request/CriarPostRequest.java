package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarPostRequest {

    private Usuario autor;

    @NotEmpty
    private String descricao;

    private String imagem;

    @NotEmpty
    private String titulo;

    private Double valor;

    @NotNull
    private Boolean isPrivado;
}
