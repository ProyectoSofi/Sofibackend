package co.edu.javeriana.seshat.sofiplus.Modules.src.Recurso;

import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Recurso.Methods.RegisterRecurso;
import org.springframework.stereotype.Component;

@Component
public class ModuleRecurso extends BusinessModule {
    @Override
    public void init() {
        Kernel.registerModuleRunnable("module.recurso.registerRecurso", RegisterRecurso.class);
        this.populate();
    }
}
