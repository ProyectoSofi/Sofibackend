package co.edu.javeriana.seshat.sofiplus;

import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BeanRegistration implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(
            BeanDefinitionRegistry bdr) throws BeansException {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addClassLoaders(this.getClass().getClassLoader());
        builder.forPackage("co.edu.javeriana.seshat.sofiplus.Modules.src");
        Reflections reflections = new Reflections(builder);
        Set<Class<?>> methods = reflections.getTypesAnnotatedWith(ModuleMethod.class);
        System.out.println("Metodos: " + methods);
        methods.forEach(method -> {
            bdr.registerBeanDefinition(method.getCanonicalName(), BeanDefinitionBuilder.genericBeanDefinition(method).setScope("prototype").getBeanDefinition());
        });
    }

    @Override
    public void postProcessBeanFactory(
            ConfigurableListableBeanFactory clbf) throws BeansException {
    }
}
