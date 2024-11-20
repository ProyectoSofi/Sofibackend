package co.edu.javeriana.seshat.sofiplus.Controllers;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.UsuarioEntity;
import co.edu.javeriana.seshat.sofiplus.Security.CredencialesLogin;
import co.edu.javeriana.seshat.sofiplus.Security.JWTUtil;
import co.edu.javeriana.seshat.sofiplus.Security.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private DataBroker broker;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody CredencialesLogin credenciales) {
        try {
            Optional<UsuarioEntity> usuario = broker.requerirUsuarioPorEmail(credenciales.getEmail());
            if (usuario.isEmpty()) {
                throw new RuntimeException("Credenciales Invalidas");
            }
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(usuario.get().getId(), credenciales.getPassword());
            authManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(usuario.get());
            return new LoginResponse(token, usuario.get().getNombre(), usuario.get().getId(), usuario.get().getRol());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales Invalidas por AuthManager");
        }
    }
}
