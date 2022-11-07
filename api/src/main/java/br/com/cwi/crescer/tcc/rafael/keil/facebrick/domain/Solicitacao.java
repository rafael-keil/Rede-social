package br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fb_solicitacao")
public class Solicitacao {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "remetente")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario")
    @JsonIgnore
    private Usuario destinatario;

    @Column(name = "is_aceita")
    @JsonIgnore
    private Boolean isAceita;

    public Solicitacao(final Usuario remetente, final Usuario destinatario) {
        this.remetente = remetente;
        this.destinatario = destinatario;

        this.setIsAceita(false);
        this.setId(UUID.randomUUID());
    }
}
