package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.UsuarioEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.FrontEntities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@ModuleMethod
public class CrearUsuario implements ModuleRunnable {

    @Autowired
    private DataBroker broker;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        Usuario usuario = (Usuario) message.getParams();
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return broker.registrarUsuario(new UsuarioEntity(usuario)).getFrontEntity();
    }
}
