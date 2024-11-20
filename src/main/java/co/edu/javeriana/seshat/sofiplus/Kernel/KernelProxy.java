package co.edu.javeriana.seshat.sofiplus.Kernel;

public class KernelProxy {
    ModuleRunnable method;
    RequestMessage message;

    public KernelProxy(ModuleRunnable method, RequestMessage message) {
        this.method = method;
        this.message = message;
    }

    public Object run() throws AuthorizationRequiredException {
        return this.method.run(this.message);
    }
}
