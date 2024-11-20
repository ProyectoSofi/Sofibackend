package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activo {
    String codigo;
    String descripcion;
    float impuesto;
    double cantidad;
    double costo;
    double vidaUtil;
    String unidadTiempo;
    String fechaIngreso;
    String type;
}
