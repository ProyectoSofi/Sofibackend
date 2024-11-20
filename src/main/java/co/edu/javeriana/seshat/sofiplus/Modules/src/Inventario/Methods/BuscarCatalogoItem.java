package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@ModuleMethod
public class BuscarCatalogoItem implements ModuleRunnable {

    @Autowired
    private ItemEntityRepository repository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        return repository.findAllByNitFamiempresa(message.getCredentials().get().getFamiempresaID()).stream().map(ItemEntity::getFrontEntity).collect(Collectors.toList());
    }
}
