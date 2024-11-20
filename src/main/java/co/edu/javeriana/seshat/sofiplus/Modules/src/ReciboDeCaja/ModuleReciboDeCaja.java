package co.edu.javeriana.seshat.sofiplus.Modules.src.ReciboDeCaja;

import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import org.springframework.stereotype.Component;

@Component
public class ModuleReciboDeCaja extends BusinessModule {
    @Override
    public void init(){
        this.populate();
    }
}
