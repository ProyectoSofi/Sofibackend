package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.FrontEntities.Activo;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Item;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.ItemCarga;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activo/activo")
public class ModuleActivoController {
    @GetMapping("/catalog")
    public List<Activo> catalogoActivos() throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (List<Activo>) Kernel.processRequest("activo/activo/catalog/get", new RequestMessage(null, token)).run();
    }

    @PostMapping
    public Activo crearActivo(@RequestBody Activo activo) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Activo) Kernel.processRequest("activo/activo/post", new RequestMessage(activo, token)).run();
    }

}
