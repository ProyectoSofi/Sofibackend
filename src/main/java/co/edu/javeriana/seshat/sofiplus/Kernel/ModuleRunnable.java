package co.edu.javeriana.seshat.sofiplus.Kernel;

public interface ModuleRunnable {
    Object run(RequestMessage message) throws AuthorizationRequiredException;
}
