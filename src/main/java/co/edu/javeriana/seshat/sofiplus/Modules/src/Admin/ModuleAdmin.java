package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin;

import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Methods.BuscarCatalogoFamiempresa;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Methods.BuscarCatalogoUsuario;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Methods.CrearFamiempresa;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Methods.CrearUsuario;
import org.springframework.stereotype.Component;

@Component
public class ModuleAdmin extends BusinessModule {
    @Override
    public void init() {
        this.catalog.put("admin/famiempresa/post", CrearFamiempresa.class);
        this.catalog.put("admin/famiempresa/catalog/get", BuscarCatalogoFamiempresa.class);
        this.catalog.put("admin/usuario/post", CrearUsuario.class);
        this.catalog.put("admin/usuario/catalog/get", BuscarCatalogoUsuario.class);
        this.populate();
    }
}
