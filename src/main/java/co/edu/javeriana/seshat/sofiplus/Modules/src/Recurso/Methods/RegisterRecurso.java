package co.edu.javeriana.seshat.sofiplus.Modules.src.Recurso.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import org.springframework.beans.factory.annotation.Autowired;

@ModuleMethod
public class RegisterRecurso implements ModuleRunnable {

    @Autowired
    private DataBroker broker;

    @Override
    public Object run(RequestMessage message) {
//        Recurso recurso = (Recurso) message.getParams();
//        RecursoEntity nuevoRecurso = new RecursoEntity();
//        recurso.setIdRecurso(recurso.getIdRecurso());
//        recurso.setCuenta(recurso.getCuenta());
//        return broker.registrarRecurso(nuevoRecurso);
        return null;
    }
}
