package co.edu.javeriana.seshat.sofiplus.DataFacade;

import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.ReaEntity;

import java.util.List;

public class ReaConsolidated {
    private EventoEntity evento;
    private List<ReaEntity> detalles;

    public ReaConsolidated(EventoEntity evento, List<ReaEntity> detalles) {
        this.evento = evento;
        this.detalles = detalles;
    }

    public EventoEntity getEvento() {
        return evento;
    }

    public void setEvento(EventoEntity evento) {
        this.evento = evento;
    }

    public List<ReaEntity> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ReaEntity> detalles) {
        this.detalles = detalles;
    }
}
