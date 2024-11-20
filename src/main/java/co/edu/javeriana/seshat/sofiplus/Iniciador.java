package co.edu.javeriana.seshat.sofiplus;

import co.edu.javeriana.seshat.sofiplus.Modules.BusinessModule;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Iniciador implements CommandLineRunner {

    @Autowired
    ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addClassLoaders(this.getClass().getClassLoader());
        builder.forPackage("co.edu.javeriana.seshat.sofiplus.Modules.src");
        Reflections reflections = new Reflections(builder);
        Set<Class<? extends BusinessModule>> modules = reflections.getSubTypesOf(BusinessModule.class);
        System.out.println("Modulos: " + modules);
        modules.forEach(module -> {
            ctx.getBean(module).init();
        });
    }
}
