package co.edu.javeriana.seshat.sofiplus.Kernel;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Kernel {
    private static final HashMap<String, Class<? extends ModuleRunnable>> index = new HashMap<>();

    public static void registerModuleRunnable(String uri, Class<? extends ModuleRunnable> method) {
        index.put(uri, method);
    }

    public static KernelProxy processRequest(String uri, RequestMessage message) {
        ModuleRunnable method = SpringApplicationContext.getBean(index.get(uri));
        return new KernelProxy(method, message);
    }
}
