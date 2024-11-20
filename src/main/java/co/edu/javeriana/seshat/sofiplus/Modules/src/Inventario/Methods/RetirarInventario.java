package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Mensajes.RetiroItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

@ModuleMethod
public class RetirarInventario implements ModuleRunnable {

    @Autowired
    ItemEntityRepository repository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        RetiroItem retiroItem = (RetiroItem) message.getParams();
        try {
            ItemEntity entity = new ItemEntity(retiroItem.getItem(), message.getCredentials().get().getFamiempresaID());
            entity.setCantidad(entity.getCantidad() - retiroItem.getCantidad());
            return repository.save(entity).getFrontEntity();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
