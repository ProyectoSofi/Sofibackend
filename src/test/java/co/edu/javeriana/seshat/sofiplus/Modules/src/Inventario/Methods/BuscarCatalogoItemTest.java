package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class BuscarCatalogoItemTest {

    @Mock
    private ItemEntityRepository repository;

    private ItemEntity item;

    private RequestMessage message;

    private JWTToken token;

    @InjectMocks
    private BuscarCatalogoItem buscarCatalogoItem;

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
        message = new RequestMessage(token.getFamiempresaID(), token);
    }

    @Test
    void run() {
        when(repository.findAllByNitFamiempresa(token.getFamiempresaID())).thenReturn(List.of(item));
        try {
            assertNotNull(buscarCatalogoItem.run(message));
        } catch (AuthorizationRequiredException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void runException() {
        assertThrows(AuthorizationRequiredException.class, () -> {
            buscarCatalogoItem.run(new RequestMessage(token.getFamiempresaID()));
        });
    }
}