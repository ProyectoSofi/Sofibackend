package co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.PersonaEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Entities.ComprobanteEgresoMetadata;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Entities.ComprobanteEgresoMetadataRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.FrontEntities.ComprobanteEgreso;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Optional;

@ModuleMethod
public class ConsolidarEgreso implements ModuleRunnable {

    @Autowired
    ComprobanteEgresoMetadataRepository repository;

    @Autowired
    DataBroker broker;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        EventoEntity evento = (EventoEntity) message.getParams();
        ComprobanteEgreso egreso = new ComprobanteEgreso();
        egreso.setId(evento.getIdEvento());
        egreso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(evento.getFecha()));
        PersonaEntity cliente = broker.requerirAgente(evento.getAgenteExterno());
        egreso.setPagadoA(cliente.getIdPersona());
        egreso.setTerceroNombre(cliente.getNombre());
        Optional<ComprobanteEgresoMetadata> metadataOptional = repository.findById(evento.getIdEvento());
        if (metadataOptional.isPresent()) {
            ComprobanteEgresoMetadata metadata = metadataOptional.get();
            egreso.setCantidad(metadata.getCantidad());
            egreso.setPorConceptoDe(metadata.getPorConceptoDe());
            egreso.setTipoGasto(metadata.getTipoPago());
        }
        return egreso;
    }
}
