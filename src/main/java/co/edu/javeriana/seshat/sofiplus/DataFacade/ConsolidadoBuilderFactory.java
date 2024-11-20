package co.edu.javeriana.seshat.sofiplus.DataFacade;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsolidadoBuilderFactory {
    @Autowired
    private DataBroker broker;

    public ConsolidadoBuilder getBuilder(String nitFamiempresa) {
        return new ConsolidadoBuilder(nitFamiempresa, this.broker);
    }
}
