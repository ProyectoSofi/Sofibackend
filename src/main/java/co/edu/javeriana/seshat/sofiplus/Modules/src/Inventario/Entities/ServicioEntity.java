package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities;

import co.edu.javeriana.seshat.sofiplus.DataFacade.FrontEntity;
import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Servicio;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@IdClass(ServicioEntityPK.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ServicioEntity implements FrontEntity<Servicio> {
    @Id
    private String nitFamiempresa;
    @Id
    private String codigo;
    private String descripcion;
    private double impuesto;
    private double precio;
    private Date fechaCreacion;

    private String type;

    public ServicioEntity(Servicio servicio, String nitFamiempresa) throws ParseException {
        this.nitFamiempresa = nitFamiempresa;
        this.codigo = servicio.getCodigo();
        this.descripcion = servicio.getDescripcion();
        this.impuesto = servicio.getImpuesto();
        this.precio = servicio.getPrecio();
        this.fechaCreacion = DateFromFront.format(servicio.getFechaCreacion());
        this.type = servicio.getType();
    }

    @Override
    public Servicio getFrontEntity() {
        return new Servicio(this.codigo, this.descripcion, this.impuesto, this.precio, new SimpleDateFormat("dd/MM/yyyy").format(this.fechaCreacion), this.type);
    }
}
