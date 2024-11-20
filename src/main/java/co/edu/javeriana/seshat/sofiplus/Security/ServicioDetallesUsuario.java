package co.edu.javeriana.seshat.sofiplus.Security;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class ServicioDetallesUsuario implements UserDetailsService {

    @Autowired
    private DataBroker broker;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioOpcional = broker.requerirUsuario(id);
        if (usuarioOpcional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        UsuarioEntity usuario = usuarioOpcional.get();
        return new User(usuario.getId(), usuario.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_FAMIEMPRESA")));
    }
}
