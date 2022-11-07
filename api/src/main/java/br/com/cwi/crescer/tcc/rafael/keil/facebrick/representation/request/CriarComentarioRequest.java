package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarComentarioRequest {

    private Usuario autor;

    @NotEmpty
    private String descricao;

    private String imagem;

}
