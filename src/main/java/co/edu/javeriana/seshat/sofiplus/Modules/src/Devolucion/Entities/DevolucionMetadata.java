package co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities;

import co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.FrontEntities.ItemDevolucion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevolucionMetadata {
    @Id
    String id;
    ItemDevolucion[] detalles;
    Date fecha;
    Date fechaVencimiento;
    String tipoPago;
    double total;
    double costoTotal;
    double totalItems;
    double totalServicios;
}
