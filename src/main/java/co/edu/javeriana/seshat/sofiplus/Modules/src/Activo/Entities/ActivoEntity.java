package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities;

import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.FrontEntities.Activo;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityPK;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Item;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Entity
@IdClass(ActivoEntityPK.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActivoEntity {
    @Id
    private String nitFamiempresa;
    @Id
    private String codigo;
    private String descripcion;
    private float impuesto;
    private double cantidad;
    private double costo;
    private double vidaUtil;
    private String unidadTiempo;
    private Date fechaIngreso;
    private String type;

    public ActivoEntity(Activo activo, String nitFamiempresa) throws ParseException {
        this.codigo = activo.getCodigo();
        this.descripcion = activo.getDescripcion();
        this.impuesto = activo.getImpuesto();
        this.cantidad = activo.getCantidad();
        this.costo = activo.getCosto();
        this.vidaUtil = activo.getVidaUtil();
        this.unidadTiempo = activo.getUnidadTiempo();
        this.fechaIngreso = DateFromFront.format(activo.getFechaIngreso());
        this.type = activo.getType();
        this.nitFamiempresa = nitFamiempresa;
    }

    public Activo getFrontEntity() {
        Activo activo = new Activo();
        activo.setCodigo(this.getCodigo());
        activo.setDescripcion(this.getDescripcion());
        activo.setImpuesto(this.getImpuesto());
        activo.setCantidad(this.getCantidad());
        activo.setCosto(this.getCosto());
        activo.setVidaUtil(this.getVidaUtil());
        activo.setUnidadTiempo(this.getUnidadTiempo());
        activo.setFechaIngreso(new SimpleDateFormat("dd/MM/yyyy").format(this.getFechaIngreso()));
        activo.setType(this.getType());
        return activo;
    }
}
