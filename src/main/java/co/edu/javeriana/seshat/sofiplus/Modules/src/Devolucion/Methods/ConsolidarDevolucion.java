package co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.PersonaEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities.DevolucionMetadata;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities.DevolucionMetadataRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.FrontEntities.Devolucion;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Optional;

@ModuleMethod
public class ConsolidarDevolucion implements ModuleRunnable {

    @Autowired
    DevolucionMetadataRepository repository;

    @Autowired
    DataBroker broker;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        EventoEntity evento = (EventoEntity) message.getParams();
        Devolucion devolucion = new Devolucion();
        devolucion.setId(evento.getIdEvento());
        devolucion.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(evento.getFecha()));
        PersonaEntity cliente = broker.requerirAgente(evento.getAgenteExterno());
        devolucion.setClienteID(cliente.getIdPersona());
        devolucion.setClienteNombre(cliente.getNombre());
        Optional<DevolucionMetadata> metadataOptional = repository.findById(evento.getIdEvento());
        if (metadataOptional.isPresent()) {
            DevolucionMetadata metadata = metadataOptional.get();
            devolucion.setDetalles(metadata.getDetalles());
            devolucion.setTipoPago(metadata.getTipoPago());
            devolucion.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy").format(metadata.getFechaVencimiento()));
            devolucion.setTotal(metadata.getTotal());
            devolucion.setCostoTotal(metadata.getCostoTotal());
            devolucion.setTotalItems(metadata.getTotalItems());
            devolucion.setTotalServicios(metadata.getTotalServicios());
        }
        return devolucion;
    }
}
