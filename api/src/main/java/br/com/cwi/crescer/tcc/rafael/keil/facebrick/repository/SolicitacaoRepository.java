package br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Solicitacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {

    Solicitacao findById(UUID fromString);

    @Query("SELECT CASE " +
            "WHEN s.remetente.email = :email THEN s.destinatario.email " +
            "ELSE s.remetente.email END " +
            "FROM Solicitacao s " +
            "WHERE s.isAceita = TRUE AND (s.remetente.email = :email OR s.destinatario.email = :email)")
    List<String> listarEmailAmigos(String email);

    @Query("SELECT CASE " +
            "WHEN s.remetente.email = :email THEN s.destinatario.email " +
            "ELSE s.remetente.email END " +
            "FROM Solicitacao s " +
            "WHERE s.remetente.email = :email OR s.destinatario.email = :email")
    List<String> listarEmailSolicitacao(String email);

    @Query("SELECT s FROM Solicitacao s WHERE s.remetente.email = :emailRemetente AND s.destinatario.email = :emailDestinatario")
    Solicitacao procurarSolicitacaoPorEmail(String emailRemetente, String emailDestinatario);

    @Query("SELECT s FROM Solicitacao s WHERE s.destinatario.email = :email AND s.isAceita = FALSE")
    Page<Solicitacao> buscarSolicitacaoEmAberto(String email, Pageable pageable);
}