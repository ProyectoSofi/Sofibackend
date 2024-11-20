package co.edu.javeriana.seshat.sofiplus.DataFacade;

import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.ConsolidadoEntity;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.ConsolidadoEntityPK;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ConsolidadoBuilder {
    String nitFamiempresa;
    List<ConsolidadoEntity> batch;
    DataBroker broker;

    public ConsolidadoBuilder(String nitFamiempresa, DataBroker broker) {
        this.nitFamiempresa = nitFamiempresa;
        this.broker = broker;
        this.batch = new ArrayList<>();
    }

    public ConsolidadoBuilder sumar(String idRecurso, double afectacion) {
        Optional<ConsolidadoEntity> consolidado = broker.requerirConsolidado(new ConsolidadoEntityPK(idRecurso, nitFamiempresa));
        if (consolidado.isEmpty()) {
            this.batch.add(new ConsolidadoEntity(idRecurso, this.nitFamiempresa, afectacion));
        } else {
            this.batch.add(new ConsolidadoEntity(idRecurso, this.nitFamiempresa, consolidado.get().getSaldo() + afectacion));
        }
        return this;
    }

    public ConsolidadoBuilder restar(String idRecurso, double afectacion) {
        Optional<ConsolidadoEntity> consolidado = broker.requerirConsolidado(new ConsolidadoEntityPK(idRecurso, nitFamiempresa));
        if (consolidado.isEmpty()) {
            this.batch.add(new ConsolidadoEntity(idRecurso, this.nitFamiempresa, afectacion * -1));
        } else {
            this.batch.add(new ConsolidadoEntity(idRecurso, this.nitFamiempresa, consolidado.get().getSaldo() + (afectacion * -1)));
        }
        return this;
    }

    public ConsolidadoBuilder ejecutar(){
        this.broker.registrarConsolidados(this.batch);
        return this;
    }
}
