package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.UsuarioEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.UsuarioEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@ModuleMethod
public class BuscarCatalogoUsuario implements ModuleRunnable {

    @Autowired
    private UsuarioEntityRepository usuarioEntityRepository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        return usuarioEntityRepository.findAll().stream().map(UsuarioEntity::getFrontEntity).collect(Collectors.toList());
    }
}
