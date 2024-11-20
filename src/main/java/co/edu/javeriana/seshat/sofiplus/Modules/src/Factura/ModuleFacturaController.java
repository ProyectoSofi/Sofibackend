package co.edu.javeriana.seshat.sofiplus.Modules.src.Factura;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities.ParametrosBusquedaFactura;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities.TipoBusquedaEnum;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.FrontEntities.Factura;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class ModuleFacturaController {

    @GetMapping
    public Factura buscarFactura(@RequestParam String id) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Factura) Kernel.processRequest("factura/get", new RequestMessage(id, token)).run();
    }

    @GetMapping("/filtrar")
    public List<Factura> buscarFacturas(@RequestParam TipoBusquedaEnum tipo, @RequestParam(required = false) String fecha, @RequestParam(required = false) String terceroID) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (List<Factura>) Kernel.processRequest("factura/filtrar/get", new RequestMessage(new ParametrosBusquedaFactura(tipo, fecha, terceroID), token)).run();
    }

    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) throws AuthorizationRequiredException {
        JWTToken token = (JWTToken) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Factura) Kernel.processRequest("factura/post", new RequestMessage(factura, token)).run();
    }
}
