package co.edu.javeriana.seshat.sofiplus.Modules.src.ComprobanteEgreso.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteEgresoMetadata {
    @Id
    String id;
    Date fecha;
    double cantidad;
    String porConceptoDe;
    String tipoPago;
}
