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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CrearItemTest {

    @Mock
    private ItemEntityRepository repository;

    @Mock
    private ConsolidadoBuilderFactory consolidadoBuilderFactory;

    @Mock
    private DataBroker broker;

    @InjectMocks
    private CrearItem crearItem;

    private ItemEntity item;

    private RequestMessage message;

    private JWTToken token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        token = new JWTToken("1013632535", "Jairo Vanegas", "jairo.vanegas@javeriana.edu.co", "1013632535-3", "ADMIN");
        item = new ItemEntity(token.getFamiempresaID(), "P1", "", 0.0, "", "", 0.0, 0.0, 0.0, 0.0, new Date(0), "ITEM");
        message = new RequestMessage(item.getFrontEntity(), token);
    }

    @Test
    void run() {
        when(consolidadoBuilderFactory.getBuilder(token.getFamiempresaID())).thenReturn(new ConsolidadoBuilder(token.getFamiempresaID(), broker));
        when(repository.save(item)).thenReturn(item);
        try {
            Item frontItem = (Item) crearItem.run(message);
            assertEquals(frontItem, item.getFrontEntity());

        } catch (AuthorizationRequiredException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void runException() {
        assertThrows(AuthorizationRequiredException.class, () -> {
            crearItem.run(new RequestMessage(token.getFamiempresaID()));
        });
    }

    @Test
    void runParseException() {
        assertThrows(RuntimeException.class, () -> {
            crearItem.run(new RequestMessage(new Item("P1", "TestItem", 20, "Unidades", "Unidad", 19, 1000, 20000, 2000, "hola mundo", "ITEM"), new JWTToken("1013632535", "Jairo Vanegas", "jairo.vanegas@javeriana.edu.co", "1013632535-3", "ADMIN")));
        });
    }
}