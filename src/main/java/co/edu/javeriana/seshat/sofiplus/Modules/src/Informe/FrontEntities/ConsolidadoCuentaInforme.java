package co.edu.javeriana.seshat.sofiplus.Modules.src.Informe.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ConsolidadoCuentaInforme {
    double total;
    List<CuentaDetalleInforme> detalles;

    public ConsolidadoCuentaInforme() {
        this.total = 0;
        this.detalles = new ArrayList<>();
    }

    public void procesarDetalle(CuentaDetalleInforme detalle){
        if(detalle.negativo){
            this.total -= detalle.saldo;
        }else{
            this.total += detalle.saldo;
        }
        this.detalles.add(detalle);
    }
}
