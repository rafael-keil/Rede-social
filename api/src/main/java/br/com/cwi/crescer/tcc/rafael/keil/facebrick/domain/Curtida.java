package br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fb_curtida")
@SequenceGenerator(name = "fb_seq_curtida", sequenceName = "fb_seq_curtida", allocationSize = 1)
public class Curtida {

    public Curtida(final Usuario autor, final EntradaBase pai) {
        this.autor = autor;
        this.pai = pai;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fb_seq_curtida")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "autor")
    @JsonIgnore
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "pai")
    @JsonIgnore
    private EntradaBase pai;
}
