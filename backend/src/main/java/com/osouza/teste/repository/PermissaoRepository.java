package com.osouza.teste.repository;

import com.osouza.teste.domain.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    @Query(value = " " +
            "SELECT p " +
            "FROM Usuario u " +
            "INNER JOIN Permissao p " +
            "WHERE u.id = :id")
    List<Permissao> findAllPermissoesByUsuarioId(@Param("id") Long id);

    @Query(value = " " +
            "SELECT p " +
            "FROM Usuario u " +
            "INNER JOIN Permissao p " +
            "WHERE u.nome = :login AND u.senha = :senha")
    List<Permissao> findAllPermissoesByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);

}
