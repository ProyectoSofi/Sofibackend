package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Item;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Mensajes.RetiroItem;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RetirarInventarioTest {

    @Mock
    ItemEntityRepository repository;

    @InjectMocks
    RetirarInventario retirarInventario;

    private ItemEntity expectedEntity;
    private RetiroItem receivedCommand;
    private Item expectedItem;
    private RetiroItem corruptCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Item item = new Item("P1", "TestItem", 20, "Unidades", "Unidad", 19, 1000, 20000, 2000, "01/01/2022", "ITEM");
        receivedCommand = new RetiroItem(item, 5);
        try {
            expectedEntity = new ItemEntity("1", item.getCodigo(), item.getDescripcion(), item.getCantidad() - 5, item.getDimension(), item.getUnidadPreferida(), item.getImpuesto(), item.getCosteUnitario(), item.getCosteTotal(), item.getPrecio(), DateFromFront.format(item.getFechaIngreso()), item.getType());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        expectedItem = expectedEntity.getFrontEntity();
        corruptCommand = new RetiroItem(new Item("P1", "TestItem", 20, "Unidades", "Unidad", 19, 1000, 20000, 2000, "hola mundo", "ITEM"), 5);
    }

    @Test
    void run() {
        when(repository.save(expectedEntity)).thenReturn(expectedEntity);
        try {
            Item result = (Item) retirarInventario.run(new RequestMessage(receivedCommand, new JWTToken("1", "TestUser", "test@javeriana.edu.co", "1", "USUARIO")));
            assertEquals(result.getCantidad(), expectedItem.getCantidad());
        } catch (AuthorizationRequiredException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void runException() {
        assertThrows(AuthorizationRequiredException.class, () -> {
            retirarInventario.run(new RequestMessage(receivedCommand));
        });
    }

    @Test
    void runParseException() {
        assertThrows(RuntimeException.class, () -> {
            retirarInventario.run(new RequestMessage(corruptCommand, new JWTToken("1013632535", "Jairo Vanegas", "jairo.vanegas@javeriana.edu.co", "1013632535-3", "ADMIN")));
        });
    }
}