package co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.FrontEntities.ComprobanteEgreso;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities.ParametrosBusquedaFactura;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities.TipoBusquedaEnum;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/egreso")
public class ModuleEgresoController {

    @GetMapping
    public ComprobanteEgreso buscarEgreso(@RequestParam String id) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (ComprobanteEgreso) Kernel.processRequest("egreso/get", new RequestMessage(id, token)).run();
    }

    @GetMapping("/filtrar")
    public List<ComprobanteEgreso> buscarEgresos(@RequestParam TipoBusquedaEnum tipo, @RequestParam(required = false) String fecha, @RequestParam(required = false) String terceroID) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (List<ComprobanteEgreso>) Kernel.processRequest("egreso/filtrar/get", new RequestMessage(new ParametrosBusquedaFactura(tipo, fecha, terceroID), token)).run();
    }

    @PostMapping
    public ComprobanteEgreso crearEgreso(@RequestBody ComprobanteEgreso egreso) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (ComprobanteEgreso) Kernel.processRequest("egreso/post", new RequestMessage(egreso, token)).run();
    }
}
