package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.FamiempresaEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.FamiempresaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@ModuleMethod
public class BuscarCatalogoFamiempresa implements ModuleRunnable {

    @Autowired
    private FamiempresaEntityRepository famiempresaEntityRepository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        return famiempresaEntityRepository.findAll().stream().map(FamiempresaEntity::getFrontEntity).collect(Collectors.toList());
    }
}
