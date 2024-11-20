package co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteEgreso {
    String id;
    String fecha;
    double cantidad;
    String porConceptoDe;
    String pagadoA;
    String terceroNombre;
    String tipoGasto;
}
