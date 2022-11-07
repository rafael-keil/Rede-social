package br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    Comentario findById(UUID id);

    List<Comentario> findByPaiId(UUID id);
}
