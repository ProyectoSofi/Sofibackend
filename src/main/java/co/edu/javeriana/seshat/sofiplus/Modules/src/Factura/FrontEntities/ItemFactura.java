package co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemFactura {
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
