package co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, String> {
    List<ClienteEntity> findAllByNitFamiempresa(String nitFamiempresa);
}