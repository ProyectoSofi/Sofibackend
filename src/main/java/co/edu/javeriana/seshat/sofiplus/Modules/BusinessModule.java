package co.edu.javeriana.seshat.sofiplus.Modules;

import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;

import java.util.HashMap;
import java.util.Map;

public abstract class BusinessModule {
    protected HashMap<String, Class<? extends ModuleRunnable>> catalog = new HashMap<>();
    protected void populate(){
        for (Map.Entry<String, Class<? extends ModuleRunnable>> entry : this.catalog.entrySet()) {
            Kernel.registerModuleRunnable(entry.getKey(), entry.getValue());
        }
    }
     public abstract void init();
}
