package co.edu.javeriana.seshat.sofiplus.Modules.src.Informe.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.DataBroker;
import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.ConsolidadoEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Informe.FrontEntities.ConsolidadoCuentaInforme;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Informe.FrontEntities.CuentaDetalleInforme;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Informe.FrontEntities.InformeEstadoResultados;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

enum TipoCuenta {
    INGRESO,
    COSTO,
    GASTO
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class PUC {
    String puc;
    String cuenta;
    TipoCuenta tipoCuenta;
    boolean negativo;
}

class CatalogoPUC {
    static Map<String, PUC> getCatalogo() {
        Map<String, PUC> catalogo = new HashMap<>();
        //INGRESOS
        catalogo.put("4195", new PUC("4195", "INGRESOS POR SERVICIOS", TipoCuenta.INGRESO, false));
        catalogo.put("4135", new PUC("4135", "INGRESOS POR VENTAS", TipoCuenta.INGRESO, false));
        catalogo.put("4275", new PUC("4275", "DEVOLUCIONES", TipoCuenta.INGRESO, true));
        //COSTOS
        catalogo.put("6135", new PUC("6135", "COSTOS POR INSUMOS", TipoCuenta.COSTO, false));
        catalogo.put("6195", new PUC("6195", "COSTOS DE VENTA", TipoCuenta.COSTO, false));
        //GASTOS
        catalogo.put("5105", new PUC("5105", "GASTOS DE PERSONAL", TipoCuenta.GASTO, false));
        catalogo.put("5115", new PUC("5115", "GASTO EN IMPUESTOS", TipoCuenta.GASTO, false));
        catalogo.put("5120", new PUC("5120", "ARRIENDO", TipoCuenta.GASTO, false));
        catalogo.put("5145", new PUC("5145", "MANTENIMIENTO", TipoCuenta.GASTO, false));
        catalogo.put("5160", new PUC("5160", "DEPRECIACION GASTO", TipoCuenta.GASTO, false));
        catalogo.put("5195", new PUC("5195", "OTROS GASTOS", TipoCuenta.GASTO, false));
        return catalogo;
    }
}

@ModuleMethod
public class GenerarInformeResultados implements ModuleRunnable {

    @Autowired
    private DataBroker broker;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        ConsolidadoCuentaInforme ingresos = new ConsolidadoCuentaInforme();
        ConsolidadoCuentaInforme costos = new ConsolidadoCuentaInforme();
        ConsolidadoCuentaInforme gastos = new ConsolidadoCuentaInforme();
        List<ConsolidadoEntity> consolidados = broker.requerirConsolidadosPorFamiempresa(message.getCredentials().get().getFamiempresaID());
        Map<String, PUC> catalogo = CatalogoPUC.getCatalogo();
        for (ConsolidadoEntity consolidado :
                consolidados) {
            PUC puc = catalogo.get(consolidado.getIdRecurso());
            if(puc!=null){
                CuentaDetalleInforme detalle = new CuentaDetalleInforme(puc.puc, puc.cuenta, consolidado.getSaldo(), puc.negativo);
                switch (puc.tipoCuenta) {
                    case INGRESO:
                        ingresos.procesarDetalle(detalle);
                        break;
                    case COSTO:
                        costos.procesarDetalle(detalle);
                        break;
                    case GASTO:
                        gastos.procesarDetalle(detalle);
                }
            }
        }
        LocalDate localDate = LocalDate.now();
        Calendar cal = Calendar.getInstance();
        InformeEstadoResultados informe = new InformeEstadoResultados(
                String.valueOf(cal.getActualMaximum(Calendar.DATE)),
                localDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "CO")),
                String.valueOf(localDate.getYear()),
                ingresos,
                costos,
                gastos,
                ingresos.getTotal() - costos.getTotal(),
                ingresos.getTotal() - costos.getTotal() - gastos.getTotal()
        );
        return informe;
    }
}
