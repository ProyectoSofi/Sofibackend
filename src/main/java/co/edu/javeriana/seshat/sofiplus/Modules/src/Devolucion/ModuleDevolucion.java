package co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion;

import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Methods.BuscarDevolucion;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Methods.BuscarDevoluciones;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Methods.ConsolidarDevolucion;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Methods.CrearDevolucion;
import org.springframework.stereotype.Component;

@Component
public class ModuleDevolucion extends BusinessModule {
    @Override
    public void init() {
        this.catalog.put("devolucion/post", CrearDevolucion.class);
        this.catalog.put("devolucion.consolidarDevolucion", ConsolidarDevolucion.class);
        this.catalog.put("devolucion/get", BuscarDevolucion.class);
        this.catalog.put("devolucion/filtrar/get", BuscarDevoluciones.class);
        this.populate();
    }
}
