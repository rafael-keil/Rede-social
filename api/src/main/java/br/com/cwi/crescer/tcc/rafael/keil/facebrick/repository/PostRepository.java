package br.com.cwi.crescer.tcc.rafael.keil.facebrick.repository;

import br.com.cwi.crescer.tcc.rafael.keil.facebrick.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findById(UUID fromString);

    @Query(value = "SELECT * FROM fb_post post " +
            "INNER JOIN fb_entrada_base entrada_base ON post.id=entrada_base.id " +
            "LEFT OUTER JOIN fb_usuario usuario ON entrada_base.autor=usuario.id " +
            "WHERE usuario.email IN :emailList " +
            "ORDER BY entrada_base.data DESC", nativeQuery = true)
    Page<Post> procurarPostList(List<String> emailList, Pageable pageable);

    @Query("SELECT p FROM Post p " +
            "WHERE p.autor.email = :email AND " +
            "(:isAmigo = TRUE OR p.isPrivado = FALSE) " +
            "ORDER BY p.data DESC")
    Page<Post> procurarPostPessoa(String email, Boolean isAmigo, Pageable pageable);
}