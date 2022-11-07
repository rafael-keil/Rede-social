package br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurtidaRepository extends JpaRepository<Curtida, Integer> {

    Curtida findByPaiIdAndAutorEmail(UUID pai, String email);
}
