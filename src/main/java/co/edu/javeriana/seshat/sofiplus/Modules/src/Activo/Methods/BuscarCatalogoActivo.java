package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities.ActivoEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities.ActivoEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@ModuleMethod
public class BuscarCatalogoActivo implements ModuleRunnable {

    @Autowired
    private ActivoEntityRepository repository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        return repository.findAllByNitFamiempresa(message.getCredentials().get().getFamiempresaID()).stream().map(ActivoEntity::getFrontEntity).collect(Collectors.toList());
    }
}
