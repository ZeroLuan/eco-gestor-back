package br.com.ecogestor.login.repository;

import br.com.ecogestor.login.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    @Query("SELECT e FROM Usuario e WHERE e.email = :email")
    Optional<Usuario> procurarUsuarioPorEmail(String email);

}
