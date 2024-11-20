package co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities.ParametrosBusquedaDevolucion;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities.TipoBusquedaEnum;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.FrontEntities.Devolucion;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devolucion")
public class ModuleDevolucionController {

    @GetMapping
    public Devolucion buscarDevolucion(@RequestParam String id) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Devolucion) Kernel.processRequest("devolucion/get", new RequestMessage(id, token)).run();
    }

    @GetMapping("/filtrar")
    public List<Devolucion> buscarDevolucion(@RequestParam TipoBusquedaEnum tipo, @RequestParam String fecha) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (List<Devolucion>) Kernel.processRequest("devolucion/filtrar/get", new RequestMessage(new ParametrosBusquedaDevolucion(tipo, fecha), token)).run();
    }

    @PostMapping
    public Devolucion crearDevolucion(@RequestBody Devolucion devolucion) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Devolucion) Kernel.processRequest("devolucion/post", new RequestMessage(devolucion, token)).run();
    }
}
