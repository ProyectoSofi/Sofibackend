package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Mensajes;

import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetiroItem {
    Item item;
    double cantidad;
}
