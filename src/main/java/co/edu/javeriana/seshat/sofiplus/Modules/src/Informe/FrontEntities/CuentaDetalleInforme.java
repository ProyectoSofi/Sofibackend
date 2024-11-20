package co.edu.javeriana.seshat.sofiplus.Modules.src.Informe.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDetalleInforme {
    String puc;
    String cuenta;
    double saldo;
    boolean negativo;
}
