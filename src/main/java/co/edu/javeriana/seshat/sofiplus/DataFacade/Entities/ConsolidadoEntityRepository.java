package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsolidadoEntityRepository extends JpaRepository<ConsolidadoEntity, ConsolidadoEntityPK> {
    public List<ConsolidadoEntity> findAllByNitFamiempresa(String nitFamiempresa);
}