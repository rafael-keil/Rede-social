package br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fb_comentario")
public class Comentario extends EntradaBase {

    public Comentario(final Usuario autor, final String descricao, final String imagem, final EntradaBase pai) {
        super(autor, descricao, imagem);
        this.pai = pai;
    }

    @ManyToOne
    @JsonIgnore
    private EntradaBase pai;

}
