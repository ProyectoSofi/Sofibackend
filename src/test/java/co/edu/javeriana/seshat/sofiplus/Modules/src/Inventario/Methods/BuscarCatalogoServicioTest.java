package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ServicioEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ServicioEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class BuscarCatalogoServicioTest {

    @Mock
    private ServicioEntityRepository repository;

    private ServicioEntity servicio;

    private RequestMessage message;

    private JWTToken token;

    @InjectMocks
    private BuscarCatalogoServicio buscarCatalogoServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        token = new JWTToken("1013632535", "Jairo Vanegas", "jairo.vanegas@javeriana.edu.co", "1013632535-3", "ADMIN");
        servicio = new ServicioEntity(token.getFamiempresaID(), "S1", "Servicio", 0.0, 0.0, new Date(0), "SERVICIO");
        message = new RequestMessage(token.getFamiempresaID(), token);
    }

    @Test
    void run() {
        when(repository.findAllByNitFamiempresa(token.getFamiempresaID())).thenReturn(List.of(servicio));
        try {
            assertNotNull(buscarCatalogoServicio.run(message));
        } catch (AuthorizationRequiredException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void runException() {
        assertThrows(AuthorizationRequiredException.class, () -> {
            buscarCatalogoServicio.run(new RequestMessage(token.getFamiempresaID()));
        });
    }
}