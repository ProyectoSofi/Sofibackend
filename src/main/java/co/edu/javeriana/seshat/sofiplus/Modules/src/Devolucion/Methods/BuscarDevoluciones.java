package co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.*;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities.ParametrosBusquedaDevolucion;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.FrontEntities.Devolucion;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@ModuleMethod
public class BuscarDevoluciones implements ModuleRunnable {

    @Autowired
    private DataBroker broker;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        ParametrosBusquedaDevolucion parametrosBusquedaDevolucion = (ParametrosBusquedaDevolucion) message.getParams();
        List<EventoEntity> eventos;
        List<Devolucion> devoluciones = new ArrayList<>();
        switch (parametrosBusquedaDevolucion.getTipoBusqueda()) {
            case EVENTO:
                eventos = broker.requerirEventos(message.getCredentials().get().getFamiempresaID(), "DEVOLUCION");
                break;
            case EVENTOFECHA:
                try {
                    eventos = broker.requerirEventosPorFecha(message.getCredentials().get().getFamiempresaID(), "DEVOLUCION", DateFromFront.format(parametrosBusquedaDevolucion.getFecha()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                eventos = new ArrayList<>();
        }
        for (EventoEntity evento :
                eventos) {
            devoluciones.add((Devolucion) Kernel.processRequest("devolucion.consolidarDevolucion", new RequestMessage(evento)).run());
        }
        return devoluciones;
    }
}
