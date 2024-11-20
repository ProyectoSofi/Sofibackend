package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilder;
import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilderFactory;
import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ServicioEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ServicioEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Servicio;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CrearServicioTest {

    @Mock
    private ServicioEntityRepository repository;

    @Mock
    private ConsolidadoBuilderFactory consolidadoBuilderFactory;

    @Mock
    private DataBroker broker;

    @InjectMocks
    private CrearServicio crearServicio;

    private ServicioEntity servicio;

    private Servicio servicioFront;

    private RequestMessage message;

    private JWTToken token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        token = new JWTToken("1013632535", "Jairo Vanegas", "jairo.vanegas@javeriana.edu.co", "1013632535-3", "ADMIN");
        servicioFront = new Servicio("S1", "Servicio", 0.0, 0.0, new SimpleDateFormat("dd/MM/yyyy").format(new Date(0)), "SERVICIO");
        servicio = new ServicioEntity(token.getFamiempresaID(), "S1", "Servicio", 0.0, 0.0, new Date(0), "SERVICIO");
        message = new RequestMessage(servicioFront, token);
    }

    @Test
    void run() {
        when(consolidadoBuilderFactory.getBuilder(token.getFamiempresaID())).thenReturn(new ConsolidadoBuilder(token.getFamiempresaID(), broker));
        when(repository.save(any(ServicioEntity.class))).thenReturn(servicio);
        try {
            assertEquals(crearServicio.run(message), servicioFront);
        } catch (AuthorizationRequiredException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void runException() {
        assertThrows(AuthorizationRequiredException.class, () -> crearServicio.run(new RequestMessage(token.getFamiempresaID())));
    }

    @Test
    void runParseException() {
        assertThrows(RuntimeException.class, () -> {
            servicioFront.setFechaCreacion("Hola Mundo");
            crearServicio.run(new RequestMessage(servicioFront, new JWTToken("1013632535", "Jairo Vanegas", "jairo.vanegas@javeriana.edu.co", "1013632535-3", "ADMIN")));
        });
    }
}