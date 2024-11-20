package co.edu.javeriana.seshat.sofiplus.Modules.src.Factura.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametrosBusquedaFactura {
    TipoBusquedaEnum tipoBusqueda;
    String fecha;
    String terceroID;
}
