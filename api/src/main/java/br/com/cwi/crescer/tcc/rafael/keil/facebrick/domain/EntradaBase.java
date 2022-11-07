package br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fb_entrada_base")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EntradaBase {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "autor")
    @JsonIgnore
    private Usuario autor;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data")
    private LocalDateTime data;

    private String imagem;

    @OneToMany(mappedBy = "pai", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Curtida> curtida = new ArrayList<>();

    @OneToMany(mappedBy = "pai", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comentario> comentario = new ArrayList<>();

    protected EntradaBase(final Usuario autor, final String descricao, final String imagem) {
        this.autor = autor;
        this.descricao = descricao;
        this.imagem = imagem;

        this.setId();
        this.setData();
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public void setData() {
        this.data = LocalDateTime.now();
    }
}
