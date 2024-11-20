package co.edu.javeriana.seshat.sofiplus.Modules.src.Recurso;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.Kernel;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/recurso")
public class ModuleRecursoController {
    @PostMapping("/")
    int registerFamiempresa(@RequestBody HashMap<String, Object> body) throws AuthorizationRequiredException {
        return (int) Kernel.processRequest("module.recurso.registerRecurso", new RequestMessage(body)).run();
    }
}
