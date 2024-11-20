package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilder;
import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilderFactory;
import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Item;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.ItemCarga;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CargarItemTest {

    @Mock
    private ItemEntityRepository repository;

    @Mock
    private ConsolidadoBuilderFactory consolidadoBuilderFactory;

    @Mock
    private DataBroker broker;

    @InjectMocks
    private CargarItem cargarItem;

    private ItemEntity item;

    private RequestMessage message;

    private JWTToken token;

    private ItemCarga carga;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        item = new ItemEntity();
        item.setCodigo("P1");
        item.setCosteUnitario(1000);
        item.setCantidad(20);
        item.setCosteTotal(1000 * 20);
        item.setFechaIngreso(new Date(0));
        token = new JWTToken("1013632535", "Jairo Vanegas", "jairo.vanegas@javeriana.edu.co", "1013632535-3", "ADMIN");
        carga = new ItemCarga("P1", 1200, 10);
        message = new RequestMessage(carga, token);
    }

    @Test
    void run() {
        when(repository.findItemEntityByNitFamiempresaAndCodigo(token.getFamiempresaID(), carga.getCodigo())).thenReturn(item);
        when(consolidadoBuilderFactory.getBuilder(token.getFamiempresaID())).thenReturn(new ConsolidadoBuilder(token.getFamiempresaID(), broker));
        when(repository.save(item)).thenReturn(item);
        try {
            Item frontItem = (Item) cargarItem.run(message);
            assertEquals(frontItem.getCantidad(), 30);
            assertEquals(frontItem.getCosteTotal(), 32000);
            assertEquals(frontItem.getCosteUnitario(), 32000.0 / 30.0);
        } catch (AuthorizationRequiredException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void runException() {
        assertThrows(AuthorizationRequiredException.class, () -> {
            cargarItem.run(new RequestMessage(token.getFamiempresaID()));
        });
    }
}