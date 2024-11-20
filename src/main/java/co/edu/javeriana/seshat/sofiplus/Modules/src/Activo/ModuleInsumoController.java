package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.FrontEntities.Insumo;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Item;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.ItemCarga;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activo/insumo")
public class ModuleInsumoController {
    @GetMapping("/catalog")
    public List<Insumo> catalogoInsumo() throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (List<Insumo>) Kernel.processRequest("activo/insumo/catalog/get", new RequestMessage(null, token)).run();
    }

    @PostMapping
    public Insumo crearItem(@RequestBody Insumo insumo) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Insumo) Kernel.processRequest("activo/insumo/post", new RequestMessage(insumo, token)).run();
    }

}
