package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    String codigo;
    String descripcion;
    double cantidad;
    String dimension;
    String unidadPreferida;
    double impuesto;
    double costeUnitario;
    double costeTotal;
    double precio;
    String fechaIngreso;
    String type;
}
