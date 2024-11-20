package co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.*;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities.DevolucionMetadata;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities.DevolucionMetadataRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.FrontEntities.Devolucion;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.ParseException;

@ModuleMethod
public class CrearDevolucion implements ModuleRunnable {

    @Autowired
    private ReaBuilderFactory factory;
    @Autowired
    private DevolucionMetadataRepository devolucionMetadataRepository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        try {
            if (message.getCredentials().isEmpty()) {
                throw new AuthorizationRequiredException();
            }
            Devolucion devolucion = (Devolucion) message.getParams();
            ReaBuilder builder = factory.getBuilder(message.getCredentials().get().getFamiempresaID());
            builder.registrarEvento(devolucion.getId(), devolucion.getFecha(), message.getCredentials().get().getId(), devolucion.getClienteID(), "DEVOLUCION");
            if (devolucion.getTotalItems() > 0) {
                //Si se tienen productos se aumenta el inventario y se disminuye el costo de venta
                builder.sumar("14", devolucion.getCostoTotal()).restar("6195", devolucion.getCostoTotal());
            }
            //Se aumenta la cuenta de devoluciones
            builder.sumar("4275", devolucion.getTotalItems() + devolucion.getTotalServicios());
            //En cualquier caso se disminuye la caja por el valor de la devolucion
            builder.restar("1105", devolucion.getTotal());
            builder.ejecutar();
            DevolucionMetadata metadata = new DevolucionMetadata(devolucion.getId(), devolucion.getDetalles(), DateFromFront.formatUtilDate(devolucion.getFecha()), DateFromFront.formatUtilDate(devolucion.getFechaVencimiento()), devolucion.getTipoPago(), devolucion.getTotal(), devolucion.getCostoTotal(), devolucion.getTotalItems(), devolucion.getTotalServicios());
            devolucionMetadataRepository.save(metadata);
            return devolucion;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
