package co.edu.javeriana.seshat.sofiplus.DataFacade;

import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.ReaEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReaDetallesBuilder {
    String idEvento;
    String nitFamiempresa;
    List<ReaEntity> detalles;

    public ReaDetallesBuilder(String idEvento, String nitFamiempresa) {
        this.idEvento = idEvento;
        this.nitFamiempresa = nitFamiempresa;
        this.detalles = new ArrayList<>();
    }

    public ReaDetallesBuilder sumar(String idRecurso, double afectacion) {
        this.detalles.add(new ReaEntity(this.idEvento, this.nitFamiempresa, idRecurso, afectacion));
        return this;
    }

    public ReaDetallesBuilder restar(String idRecurso, double afectacion) {
        this.detalles.add(new ReaEntity(this.idEvento, this.nitFamiempresa, idRecurso, afectacion * -1));
        return this;
    }

}
