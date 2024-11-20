package co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDevolucion {
    String item;
    String codigo;
    double precio;
    double costo;
    String descuento;
    float impuesto;
    double cantidad;
    double total;
    String type;
}
