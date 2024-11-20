package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface EventoEntityRepository extends JpaRepository<EventoEntity, EventoEntityPK> {
    public List<EventoEntity> findAllByNitFamiempresaAndTipoEvento(String nitFamiempresa, String tipoEvento);

    public List<EventoEntity> findAllByNitFamiempresaAndTipoEventoAndFecha(String nitFamiempresa, String tipoEvento, Date fecha);

    public List<EventoEntity> findAllByNitFamiempresaAndTipoEventoAndAgenteExterno(String nitFamiempresa, String tipoEvento, String agenteExterno);

    public List<EventoEntity> findAllByNitFamiempresaAndTipoEventoAndAgenteInterno(String nitFamiempresa, String tipoEvento, String agenteInterno);
}