package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario;

import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods.*;
import org.springframework.stereotype.Component;

@Component
public class ModuleInventario extends BusinessModule {
    @Override
    public void init() {
        this.catalog.put("inventario/item/catalog/get", BuscarCatalogoItem.class);
        this.catalog.put("inventario/item/post", CrearItem.class);
        this.catalog.put("inventario/item/retirar", RetirarInventario.class);
        this.catalog.put("inventario/servicio/catalog/get", BuscarCatalogoServicio.class);
        this.catalog.put("inventario/servicio/post", CrearServicio.class);
        this.catalog.put("inventario/item/carga/post", CargarItem.class);
        this.populate();
    }
}
