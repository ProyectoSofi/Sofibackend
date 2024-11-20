package co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.PersonaEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities.FacturaMetadata;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities.FacturaMetadataRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.FrontEntities.Factura;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Optional;

@ModuleMethod
public class ConsolidarFactura implements ModuleRunnable {

    @Autowired
    FacturaMetadataRepository repository;

    @Autowired
    DataBroker broker;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        EventoEntity evento = (EventoEntity) message.getParams();
        Factura factura = new Factura();
        factura.setId(evento.getIdEvento());
        factura.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(evento.getFecha()));
        PersonaEntity cliente = broker.requerirAgente(evento.getAgenteExterno());
        factura.setClienteID(cliente.getIdPersona());
        factura.setClienteNombre(cliente.getNombre());
        Optional<FacturaMetadata> metadataOptional = repository.findById(evento.getIdEvento());
        if (metadataOptional.isPresent()) {
            FacturaMetadata metadata = metadataOptional.get();
            factura.setDetalles(metadata.getDetalles());
            factura.setTipoPago(metadata.getTipoPago());
            factura.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy").format(metadata.getFechaVencimiento()));
            factura.setTotal(metadata.getTotal());
            factura.setCostoTotal(metadata.getCostoTotal());
            factura.setTotalItems(metadata.getTotalItems());
            factura.setTotalServicios(metadata.getTotalServicios());
        }
        return factura;
    }
}
