package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo;

import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Methods.BuscarCatalogoActivo;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Methods.BuscarCatalogoInsumo;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Methods.CrearActivo;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Methods.CrearInsumo;
import org.springframework.stereotype.Component;

@Component
public class ModuleActivo extends BusinessModule {
    @Override
    public void init() {
        this.catalog.put("activo/activo/catalog/get", BuscarCatalogoActivo.class);
        this.catalog.put("activo/activo/post", CrearActivo.class);
        this.catalog.put("activo/insumo/catalog/get", BuscarCatalogoInsumo.class);
        this.catalog.put("activo/insumo/post", CrearInsumo.class);
        this.populate();
    }
}
