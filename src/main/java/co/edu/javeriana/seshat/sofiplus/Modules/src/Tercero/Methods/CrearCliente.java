package co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.PersonaEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Entities.ClienteEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Entities.ClienteEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.FrontEntities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

@ModuleMethod
public class CrearCliente implements ModuleRunnable {

    @Autowired
    ClienteEntityRepository repository;

    @Autowired
    DataBroker broker;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        Cliente cliente = (Cliente) message.getParams();
        ClienteEntity entity = new ClienteEntity(cliente, message.getCredentials().get().getFamiempresaID());
        PersonaEntity persona = new PersonaEntity();
        persona.setIdPersona(cliente.getClienteID());
        persona.setNombre(cliente.getNombre());
        persona.setTelefono(cliente.getTelefono());
        broker.registrarAgente(persona);
        return repository.save(entity).getFrontEntity();
    }
}
