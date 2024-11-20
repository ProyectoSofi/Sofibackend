package co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.*;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.FrontEntities.ComprobanteEgreso;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities.ParametrosBusquedaFactura;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.FrontEntities.Factura;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@ModuleMethod
public class BuscarFacturas implements ModuleRunnable {

    @Autowired
    private DataBroker broker;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        String tipoEvento = "FACTURA";
        String processURI = "factura.consolidarFactura";
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        ParametrosBusquedaFactura parametrosBusquedaEgreso = (ParametrosBusquedaFactura) message.getParams();
        List<EventoEntity> eventos;
        List<Factura> facturas = new ArrayList<>();
        switch (parametrosBusquedaEgreso.getTipoBusqueda()) {
            case EVENTO:
                eventos = broker.requerirEventos(message.getCredentials().get().getFamiempresaID(), tipoEvento);
                break;
            case EVENTOFECHA:
                try {
                    eventos = broker.requerirEventosPorFecha(message.getCredentials().get().getFamiempresaID(), tipoEvento, DateFromFront.format(parametrosBusquedaEgreso.getFecha()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case EVENTOTERCERO:
                eventos = broker.requerirEventosPorAgente(message.getCredentials().get().getFamiempresaID(), tipoEvento, true, parametrosBusquedaEgreso.getTerceroID());
                break;
            default:
                eventos = new ArrayList<>();
        }
        for (EventoEntity evento :
                eventos) {
            facturas.add((Factura) Kernel.processRequest(processURI, new RequestMessage(evento)).run());
        }
        return facturas;
    }
}
