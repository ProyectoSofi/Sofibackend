package co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.FrontEntities.Cliente;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tercero/cliente")
public class ModuleClienteController {
    @GetMapping("/catalog")
    public List<Cliente> catalogoClientes() throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (List<Cliente>) Kernel.processRequest("tercero/cliente/catalog/get", new RequestMessage(null, token)).run();
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Cliente) Kernel.processRequest("tercero/cliente/post", new RequestMessage(cliente, token)).run();
    }


}
