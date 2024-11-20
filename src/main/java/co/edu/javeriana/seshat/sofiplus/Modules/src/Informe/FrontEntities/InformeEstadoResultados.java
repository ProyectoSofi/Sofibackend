package co.edu.javeriana.seshat.sofiplus.Modules.src.Informe.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformeEstadoResultados {
    String dia;
    String mes;
    String anno;
    ConsolidadoCuentaInforme ingresos;
    ConsolidadoCuentaInforme costos;
    ConsolidadoCuentaInforme gastos;
    double utilidadBruta;
    double utilidadOperativa;

}
