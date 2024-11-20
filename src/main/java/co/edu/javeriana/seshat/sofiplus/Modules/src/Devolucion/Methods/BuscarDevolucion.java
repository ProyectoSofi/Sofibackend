package co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntityPK;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities.DevolucionMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@ModuleMethod
public class BuscarDevolucion implements ModuleRunnable {

    @Autowired
    private DataBroker broker;

    @Autowired
    DevolucionMetadataRepository devolucionMetadataRepository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        String idDevolucion = (String) message.getParams();
        Optional<EventoEntity> eventoOptional = broker.requerirEvento(new EventoEntityPK(idDevolucion, message.getCredentials().get().getFamiempresaID()));
        if (eventoOptional.isEmpty()) {
            return eventoOptional;
        }
        return Kernel.processRequest("devolucion.consolidarDevolucion", new RequestMessage(eventoOptional.get())).run();
    }
}
