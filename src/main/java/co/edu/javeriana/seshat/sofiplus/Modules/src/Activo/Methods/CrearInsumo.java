package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilder;
import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilderFactory;
import co.edu.javeriana.seshat.sofiplus.DataFacade.ReaBuilder;
import co.edu.javeriana.seshat.sofiplus.DataFacade.ReaBuilderFactory;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities.ActivoEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities.InsumoEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities.InsumoEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.FrontEntities.Activo;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.FrontEntities.Insumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.UUID;

@ModuleMethod
public class CrearInsumo implements ModuleRunnable {
    @Autowired
    private ConsolidadoBuilderFactory factory;

    @Autowired
    private InsumoEntityRepository repository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        try {
            Insumo insumo = (Insumo) message.getParams();
            ConsolidadoBuilder builder = factory.getBuilder(message.getCredentials().get().getFamiempresaID());
            //Añadir al consolidado del costo de los insumos
            builder.sumar("6135", insumo.getCosto() * insumo.getCantidad());
            builder.ejecutar();
            InsumoEntity insumoEntity = new InsumoEntity(insumo, message.getCredentials().get().getFamiempresaID());
            return repository.save(insumoEntity).getFrontEntity();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
