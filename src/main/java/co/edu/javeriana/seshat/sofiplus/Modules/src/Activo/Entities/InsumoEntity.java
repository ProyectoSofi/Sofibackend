package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities;

import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.FrontEntities.Activo;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.FrontEntities.Insumo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@IdClass(InsumoEntityPK.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsumoEntity {
    @Id
    private String nitFamiempresa;
    @Id
    private String codigo;
    private String descripcion;
    private float impuesto;
    private double cantidad;
    private double costo;
    private Date fechaIngreso;
    private String type;

    public InsumoEntity(Insumo insumo, String nitFamiempresa) throws ParseException {
        this.codigo = insumo.getCodigo();
        this.descripcion = insumo.getDescripcion();
        this.cantidad = insumo.getCantidad();
        this.impuesto = insumo.getImpuesto();
        this.costo = insumo.getCosto();
        this.fechaIngreso = DateFromFront.format(insumo.getFechaIngreso());
        this.type = insumo.getType();
        this.nitFamiempresa = nitFamiempresa;
    }

    public Insumo getFrontEntity() {
        Insumo insumo = new Insumo();
        insumo.setCodigo(this.getCodigo());
        insumo.setDescripcion(this.getDescripcion());
        insumo.setCantidad(this.getCantidad());
        insumo.setImpuesto(this.getImpuesto());
        insumo.setCosto(this.getCosto());
        insumo.setFechaIngreso(new SimpleDateFormat("dd/MM/yyyy").format(this.getFechaIngreso()));
        insumo.setType(this.getType());
        return insumo;
    }
}
