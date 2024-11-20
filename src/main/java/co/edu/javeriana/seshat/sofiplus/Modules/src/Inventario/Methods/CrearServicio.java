package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ServicioEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ServicioEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Servicio;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

@ModuleMethod
public class CrearServicio implements ModuleRunnable {

    @Autowired
    ServicioEntityRepository repository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        Servicio servicio = (Servicio) message.getParams();
        try {
            ServicioEntity entity = new ServicioEntity(servicio, message.getCredentials().get().getFamiempresaID());
            return repository.save(entity).getFrontEntity();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
