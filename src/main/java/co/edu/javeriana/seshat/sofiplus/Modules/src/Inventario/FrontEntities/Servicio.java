package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Servicio {
    String codigo;
    String descripcion;
    double impuesto;
    double precio;
    String fechaCreacion;
    String type;
}
