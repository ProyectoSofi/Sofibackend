package co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.*;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Entities.ComprobanteEgresoMetadata;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Entities.ComprobanteEgresoMetadataRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.FrontEntities.ComprobanteEgreso;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Objects;

@ModuleMethod
public class CrearEgreso implements ModuleRunnable {

    @Autowired
    ReaBuilderFactory factory;
    @Autowired
    ComprobanteEgresoMetadataRepository repository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        ComprobanteEgreso egreso = (ComprobanteEgreso) message.getParams();
        ReaBuilder builder = factory.getBuilder(message.getCredentials().get().getFamiempresaID());
        builder.registrarEvento(egreso.getId(), egreso.getFecha(), message.getCredentials().get().getId(), egreso.getPagadoA(), "EGRESO");
        try {
            //Si se esta pagando un abono a inventario
            if (Objects.equals(egreso.getTipoGasto(), "2195")) {
                //Restamos el abono de cuentas por pagar
                builder.restar("2195", egreso.getCantidad());
            } else {
                //En cualquier otro caso, lo pasamos como gasto a la cuenta adecuada
                builder.sumar(egreso.getTipoGasto(), egreso.getCantidad());
            }
            ComprobanteEgresoMetadata metadata = new ComprobanteEgresoMetadata(egreso.getId(), DateFromFront.formatUtilDate(egreso.getFecha()), egreso.getCantidad(), egreso.getPorConceptoDe(), egreso.getTipoGasto());
            builder.ejecutar();
            repository.save(metadata);
            return egreso;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
