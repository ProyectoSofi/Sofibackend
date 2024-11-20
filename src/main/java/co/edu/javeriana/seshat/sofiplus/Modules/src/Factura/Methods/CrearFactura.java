package co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.*;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.EventoEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities.FacturaMetadata;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities.FacturaMetadataRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.FrontEntities.Factura;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.ParseException;

@ModuleMethod
public class CrearFactura implements ModuleRunnable {
    @Autowired
    private ReaBuilderFactory factory;

    @Autowired
    private FacturaMetadataRepository facturaMetadataRepository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        try {
            if (message.getCredentials().isEmpty()) {
                throw new AuthorizationRequiredException();
            }
            Factura factura = (Factura) message.getParams();
            ReaBuilder builder = factory.getBuilder(message.getCredentials().get().getFamiempresaID());
            builder.registrarEvento(factura.getId(), factura.getFecha(), message.getCredentials().get().getId(), factura.getClienteID(), "FACTURA");
            if (factura.getTotalItems() > 0) {
                //Si existen productos se resta del inventario su costo y se suma al costo de ventas
                builder.restar("14", factura.getCostoTotal()).sumar("6195", factura.getCostoTotal());
                //Se aumenta el ingreso por ventas
                builder.sumar("4135", factura.getTotalItems());
            }
            if (factura.getTotalServicios() > 0) {
                //Si existen servicios se aumenta el ingreso por servicios
                builder.sumar("4195", factura.getTotalServicios());
            }
            //Se manda cualquier ingreso a caja
            builder.sumar("1105", factura.getTotal());
            //Se mandan los impuestos al gasto
            builder.sumar("5115", factura.getTotalImpuestos());
            builder.ejecutar();
            FacturaMetadata metadata = new FacturaMetadata(factura.getId(), factura.getDetalles(), DateFromFront.formatUtilDate(factura.getFecha()), DateFromFront.formatUtilDate(factura.getFechaVencimiento()), factura.getTipoPago(), factura.getTotal(), factura.getCostoTotal(), factura.getTotalItems(), factura.getTotalServicios());
            facturaMetadataRepository.save(metadata);
            return factura;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
