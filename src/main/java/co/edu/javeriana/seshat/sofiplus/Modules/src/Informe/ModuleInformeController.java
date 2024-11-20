package co.edu.javeriana.seshat.sofiplus.Modules.src.Informe;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Informe.FrontEntities.InformeEstadoResultados;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Servicio;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/informe")
public class ModuleInformeController {
    @GetMapping("/resultados")
    public InformeEstadoResultados catalogoItems() throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (InformeEstadoResultados) Kernel.processRequest("informe/resultados/get", new RequestMessage(null, token)).run();
    }
}
