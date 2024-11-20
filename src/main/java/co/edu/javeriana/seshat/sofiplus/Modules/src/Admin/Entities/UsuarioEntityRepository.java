package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, String> {
    Optional<UsuarioEntity> findUsuarioEntityByEmail(String email);
}