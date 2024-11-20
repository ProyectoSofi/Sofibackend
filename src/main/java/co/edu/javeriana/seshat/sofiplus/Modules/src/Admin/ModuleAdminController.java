package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.FrontEntities.Famiempresa;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.FrontEntities.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ModuleAdminController {

    @GetMapping("/famiempresa/catalog")
    public List<Famiempresa> buscarCatalogoFamiempresa() throws AuthorizationRequiredException {
        return (List<Famiempresa>) Kernel.processRequest("admin/famiempresa/catalog/get", new RequestMessage(null)).run();
    }

    @PostMapping("/famiempresa")
    public Famiempresa crearItem(@RequestBody Famiempresa famiempresa) throws AuthorizationRequiredException {
        return (Famiempresa) Kernel.processRequest("admin/famiempresa/post", new RequestMessage(famiempresa)).run();
    }

    @GetMapping("/usuario/catalog")
    public List<Famiempresa> buscarCatalogoUsuario() throws AuthorizationRequiredException {
        return (List<Famiempresa>) Kernel.processRequest("admin/usuario/catalog/get", new RequestMessage(null)).run();
    }

    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario) throws AuthorizationRequiredException {
        return (Usuario) Kernel.processRequest("admin/usuario/post", new RequestMessage(usuario)).run();
    }
}
