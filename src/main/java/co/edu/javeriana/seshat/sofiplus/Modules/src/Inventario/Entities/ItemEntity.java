package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities;

import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;
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
@IdClass(ItemEntityPK.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    private String nitFamiempresa;
    @Id
    private String codigo;
    private String descripcion;
    private double cantidad;
    private String dimension;
    private String unidadPreferida;
    private double impuesto;
    private double costeUnitario;
    private double costeTotal;
    private double precio;
    private Date fechaIngreso;
    private String type;

    public ItemEntity(Item item, String nitFamiempresa) throws ParseException {
        this.codigo = item.getCodigo();
        this.descripcion = item.getDescripcion();
        this.cantidad = item.getCantidad();
        this.dimension = item.getDimension();
        this.unidadPreferida = item.getUnidadPreferida();
        this.impuesto = item.getImpuesto();
        this.costeUnitario = item.getCosteUnitario();
        this.costeTotal = item.getCosteTotal();
        this.precio = item.getPrecio();
        this.fechaIngreso = DateFromFront.format(item.getFechaIngreso());
        this.type = item.getType();
        this.nitFamiempresa = nitFamiempresa;
    }

    public Item getFrontEntity() {
        Item item = new Item();
        item.setCodigo(this.getCodigo());
        item.setDescripcion(this.getDescripcion());
        item.setCantidad(this.getCantidad());
        item.setDimension(this.getDimension());
        item.setUnidadPreferida(this.getUnidadPreferida());
        item.setImpuesto(this.getImpuesto());
        item.setCosteUnitario(this.costeUnitario);
        item.setCosteTotal(this.getCosteTotal());
        item.setPrecio(this.getPrecio());
        item.setFechaIngreso(new SimpleDateFormat("dd/MM/yyyy").format(this.getFechaIngreso()));
        item.setType(this.getType());
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemEntity that = (ItemEntity) o;
        return nitFamiempresa != null && Objects.equals(nitFamiempresa, that.nitFamiempresa)
                && codigo != null && Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nitFamiempresa, codigo);
    }
}
