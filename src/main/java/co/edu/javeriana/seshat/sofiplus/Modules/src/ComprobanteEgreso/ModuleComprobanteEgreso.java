package co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso;

import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Methods.BuscarEgreso;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Methods.BuscarEgresos;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Methods.ConsolidarEgreso;
import co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Methods.CrearEgreso;
import org.springframework.stereotype.Component;

@Component
public class ModuleComprobanteEgreso extends BusinessModule {
    @Override
    public void init() {
        this.catalog.put("egreso/post", CrearEgreso.class);
        this.catalog.put("egreso.consolidarEgreso", ConsolidarEgreso.class);
        this.catalog.put("egreso/get", BuscarEgreso.class);
        this.catalog.put("egreso/filtrar/get", BuscarEgresos.class);
        this.populate();
    }
}
