package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilder;
import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilderFactory;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Item;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

@ModuleMethod
public class CrearItem implements ModuleRunnable {

    @Autowired
    ItemEntityRepository repository;

    @Autowired
    ConsolidadoBuilderFactory consolidadoBuilderFactory;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        Item item = (Item) message.getParams();
        try {
            ItemEntity entity = new ItemEntity(item, message.getCredentials().get().getFamiempresaID());
            ConsolidadoBuilder builder = consolidadoBuilderFactory.getBuilder(message.getCredentials().get().getFamiempresaID());
            builder.sumar("14", entity.getCosteTotal());
            builder.ejecutar();
            return repository.save(entity).getFrontEntity();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
