package br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fb_usuario")
@SequenceGenerator(name = "fb_seq_usuario", sequenceName = "fb_seq_usuario", allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fb_seq_usuario")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "email")
    private String email;

    @Column(name = "apelido")
    private String apelido;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "imagem")
    private String imagemPerfil;

    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Solicitacao> solicitacao = new ArrayList<>();

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EntradaBase> post = new ArrayList<>();

    public Usuario(final String nome, final String sobrenome, final String email, final String apelido, final LocalDate dataNascimento, final String imagemPerfil) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.imagemPerfil = imagemPerfil;
    }
}
