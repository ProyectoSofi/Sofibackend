package co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Entities.ClienteEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Entities.ClienteEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@ModuleMethod
public class BuscarCatalogoCliente implements ModuleRunnable {

    @Autowired
    private ClienteEntityRepository repository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        return repository.findAllByNitFamiempresa(message.getCredentials().get().getFamiempresaID()).stream().map(ClienteEntity::getFrontEntity).collect(Collectors.toList());
    }
}
