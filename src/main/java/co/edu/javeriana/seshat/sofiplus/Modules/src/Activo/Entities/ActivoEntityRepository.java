package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivoEntityRepository extends JpaRepository<ActivoEntity, String> {
    List<ActivoEntity> findAllByNitFamiempresa(String nitFamiempresa);

    ActivoEntity findItemEntityByNitFamiempresaAndCodigo(String nitFamiempresa, String codigo);
}