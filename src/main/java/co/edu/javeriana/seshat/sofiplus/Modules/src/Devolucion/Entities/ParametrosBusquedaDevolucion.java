package co.edu.javeriana.seshat.sofiplus.Modules.src.Devolucion.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametrosBusquedaDevolucion {
    TipoBusquedaEnum tipoBusqueda;
    String fecha;
}
