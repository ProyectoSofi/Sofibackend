package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarga {
    String codigo;
    double costo;
    double cantidad;
}
