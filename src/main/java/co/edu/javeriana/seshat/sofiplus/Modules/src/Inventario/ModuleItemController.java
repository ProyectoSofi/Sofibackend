package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Item;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.ItemCarga;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario/item")
public class ModuleItemController {
    @GetMapping("/catalog")
    public List<Item> catalogoItems() throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (List<Item>) Kernel.processRequest("inventario/item/catalog/get", new RequestMessage(null, token)).run();
    }

    @PostMapping
    public Item crearItem(@RequestBody Item item) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Item) Kernel.processRequest("inventario/item/post", new RequestMessage(item, token)).run();
    }

    @PostMapping("/carga")
    public Item cargarItem(@RequestBody ItemCarga carga) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Item) Kernel.processRequest("inventario/item/carga/post", new RequestMessage(carga, token)).run();
    }

}
