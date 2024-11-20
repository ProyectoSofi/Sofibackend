package co.edu.javeriana.seshat.sofiplus.DataFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReaBuilderFactory {

    @Autowired
    private DataBroker broker;

    public ReaBuilder getBuilder(String nitFamiempresa) {
        return new ReaBuilder(broker, nitFamiempresa);
    }

    public ReaBuilder getOnlyReaBuilder(String nitFamiempresa) {
        return new ReaBuilder(broker, nitFamiempresa, 1);
    }

}
