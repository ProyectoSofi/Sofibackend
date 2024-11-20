package co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    String id;
    ItemFactura[] detalles;
    String fecha;
    String FechaVencimiento;
    String clienteID;
    String clienteNombre;
    String tipoPago;
    double total;
    double costoTotal;
    double totalItems;
    double totalServicios;
    double totalImpuestos;
}
