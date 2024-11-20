package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsumoEntityRepository extends JpaRepository<InsumoEntity, String> {
    List<InsumoEntity> findAllByNitFamiempresa(String nitFamiempresa);

    InsumoEntity findItemEntityByNitFamiempresaAndCodigo(String nitFamiempresa, String codigo);
}