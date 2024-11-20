package co.edu.javeriana.seshat.sofiplus.DataFacade;

import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.ConsolidadoEntity;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.ConsolidadoEntityPK;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.ReaEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReaBuilder {
    private DataBroker broker;
    String nitFamiempresa;
    List<ReaEntity> detalles;
    List<ConsolidadoEntity> batch;
    EventoEntity evento;
    int modo;

    public ReaBuilder(DataBroker broker, String nitFamiempresa) {
        this.broker = broker;
        this.nitFamiempresa = nitFamiempresa;
        this.detalles = new ArrayList<>();
        this.batch = new ArrayList<>();
        this.modo = 0;
    }

    public ReaBuilder(DataBroker broker, String nitFamiempresa, int modo) {
        this.broker = broker;
        this.nitFamiempresa = nitFamiempresa;
        this.detalles = new ArrayList<>();
        this.batch = new ArrayList<>();
        this.modo = modo;
    }

    public ReaBuilder registrarEvento(String idEvento, String fecha, String agenteInterno, String agenteExterno, String tipo) {
        this.evento = new EventoEntity();
        this.evento.setIdEvento(idEvento);
        this.evento.setNitFamiempresa(this.nitFamiempresa);
        try {
            this.evento.setFecha(new Timestamp(DateFromFront.formatUtilDate(fecha).getTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.evento.setAgenteInterno(agenteInterno);
        this.evento.setAgenteExterno(agenteExterno);
        this.evento.setTipoEvento(tipo);
        return this;
    }

    public ReaBuilder sumar(String idRecurso, double afectacion) {
        if (this.modo == 0) {
            Optional<ConsolidadoEntity> consolidado = broker.requerirConsolidado(new ConsolidadoEntityPK(idRecurso, nitFamiempresa));
            if (consolidado.isEmpty()) {
                this.batch.add(new ConsolidadoEntity(idRecurso, this.nitFamiempresa, afectacion));
            } else {
                this.batch.add(new ConsolidadoEntity(idRecurso, this.nitFamiempresa, consolidado.get().getSaldo() + afectacion));
            }
        }
        this.detalles.add(new ReaEntity(this.evento.getIdEvento(), this.nitFamiempresa, idRecurso, afectacion));
        return this;
    }

    public ReaBuilder restar(String idRecurso, double afectacion) {
        if (this.modo == 0) {
            Optional<ConsolidadoEntity> consolidado = broker.requerirConsolidado(new ConsolidadoEntityPK(idRecurso, nitFamiempresa));
            if (consolidado.isEmpty()) {
                this.batch.add(new ConsolidadoEntity(idRecurso, this.nitFamiempresa, afectacion * -1));
            } else {
                this.batch.add(new ConsolidadoEntity(idRecurso, this.nitFamiempresa, consolidado.get().getSaldo() + (afectacion * -1)));
            }
        }
        this.detalles.add(new ReaEntity(this.evento.getIdEvento(), this.nitFamiempresa, idRecurso, afectacion * -1));
        return this;
    }

    public ReaBuilder ejecutar() {
        if (this.modo == 0) {
            this.broker.registrarConsolidados(this.batch);
        }
        this.broker.registrarRea(this.evento, this.detalles);
        return this;
    }
}
