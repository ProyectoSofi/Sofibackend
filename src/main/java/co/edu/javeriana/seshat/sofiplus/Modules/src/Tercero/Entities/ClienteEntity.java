package co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Entities;

import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.FrontEntities.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(ClienteEntityPK.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClienteEntity {
    @Id
    private String nitFamiempresa;
    @Id
    private String idPersona;
    private String nombre;
    private String telefono;
    private String observaciones;

    public ClienteEntity(Cliente cliente, String nitFamiempresa) {
        this.idPersona = cliente.getClienteID();
        this.nombre = cliente.getNombre();
        this.telefono = cliente.getTelefono();
        this.observaciones = cliente.getObservaciones();
        this.nitFamiempresa = nitFamiempresa;
    }

    public Cliente getFrontEntity() {
        Cliente cliente = new Cliente();
        cliente.setClienteID(this.idPersona);
        cliente.setNombre(this.nombre);
        cliente.setTelefono((this.telefono));
        cliente.setObservaciones(this.observaciones);
        return cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClienteEntity that = (ClienteEntity) o;
        return nitFamiempresa != null && Objects.equals(nitFamiempresa, that.nitFamiempresa)
                && idPersona != null && Objects.equals(idPersona, that.idPersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nitFamiempresa, idPersona);
    }
}
