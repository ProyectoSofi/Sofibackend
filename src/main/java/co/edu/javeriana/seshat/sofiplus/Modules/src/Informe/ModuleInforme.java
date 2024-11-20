package co.edu.javeriana.seshat.sofiplus.Modules.src.Informe;

import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Informe.Methods.GenerarInformeResultados;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods.*;
import org.springframework.stereotype.Component;

@Component
public class ModuleInforme extends BusinessModule {
    @Override
    public void init() {
        this.catalog.put("informe/resultados/get", GenerarInformeResultados.class);
        this.populate();
    }
}
