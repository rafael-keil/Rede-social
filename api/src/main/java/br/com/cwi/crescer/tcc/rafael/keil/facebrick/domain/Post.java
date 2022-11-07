package br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fb_post")
public class Post extends EntradaBase {

    public Post(final Usuario autor, final String descricao, final String imagem, final String titulo, final Double valor, final Boolean isPrivado) {
        super(autor, descricao, imagem);
        this.titulo = titulo;
        this.valor = valor;
        this.isPrivado = isPrivado;
    }

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "is_privado")
    private Boolean isPrivado;
}
