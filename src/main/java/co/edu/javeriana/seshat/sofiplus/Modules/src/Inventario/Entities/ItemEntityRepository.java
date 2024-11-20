package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, String> {
    List<ItemEntity> findAllByNitFamiempresa(String nitFamiempresa);

    ItemEntity findItemEntityByNitFamiempresaAndCodigo(String nitFamiempresa, String codigo);
}