package co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities;

import co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.FrontEntities.ItemFactura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaMetadata {
    @Id
    String id;
    ItemFactura[] detalles;
    Date fecha;
    Date fechaVencimiento;
    String tipoPago;
    double total;
    double costoTotal;
    double totalItems;
    double totalServicios;
}
