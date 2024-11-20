package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.FamiempresaEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.FamiempresaEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.FrontEntities.Famiempresa;
import org.springframework.beans.factory.annotation.Autowired;

@ModuleMethod
public class CrearFamiempresa implements ModuleRunnable {

    @Autowired
    private FamiempresaEntityRepository famiempresaEntityRepository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        Famiempresa famiempresa = (Famiempresa) message.getParams();
        return famiempresaEntityRepository.save(new FamiempresaEntity(famiempresa)).getFrontEntity();
    }
}
