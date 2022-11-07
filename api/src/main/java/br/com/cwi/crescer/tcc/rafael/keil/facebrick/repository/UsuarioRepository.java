package br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    Page<Usuario> findAllByEmailIsIn(List<String> emailList, Pageable pageable);

    @Query("SELECT u FROM Usuario u " +
            "WHERE (u.apelido LIKE %:pesquisa% OR " +
            "u.nome LIKE %:pesquisa% OR " +
            "u.sobrenome LIKE %:pesquisa% OR " +
            "u.email LIKE %:pesquisa%) AND " +
            "u.email NOT IN :emailList")
    List<Usuario> listarPesquisaUsuario(String pesquisa, List<String> emailList);
}
