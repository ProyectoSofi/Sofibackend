package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Servicio;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario/servicio")
public class ModuleServicioController {
    @GetMapping("/catalog")
    public List<Servicio> catalogoItems() throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (List<Servicio>) Kernel.processRequest("inventario/servicio/catalog/get", new RequestMessage(null, token)).run();
    }

    @PostMapping
    public Servicio crearItem(@RequestBody Servicio item) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Servicio) Kernel.processRequest("inventario/servicio/post", new RequestMessage(item, token)).run();
    }


}
