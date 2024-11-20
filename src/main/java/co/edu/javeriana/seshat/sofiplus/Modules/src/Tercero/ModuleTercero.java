package co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero;

import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Methods.BuscarCatalogoCliente;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Methods.CrearCliente;
import org.springframework.stereotype.Component;

@Component
public class ModuleTercero extends BusinessModule {
    @Override
    public void init() {
        this.catalog.put("tercero/cliente/catalog/get", BuscarCatalogoCliente.class);
        this.catalog.put("tercero/cliente/post", CrearCliente.class);
        this.populate();
    }
}
