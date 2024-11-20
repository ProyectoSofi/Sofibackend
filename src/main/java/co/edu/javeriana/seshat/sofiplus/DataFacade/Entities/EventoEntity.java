package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@IdClass(EventoEntityPK.class)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class EventoEntity {
    @Id
    private String idEvento;
    @Id
    private String nitFamiempresa;
    private Timestamp fecha;
    private String tipoEvento;
    private String agenteInterno;
    private String agenteExterno;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventoEntity that = (EventoEntity) o;
        return idEvento != null && Objects.equals(idEvento, that.idEvento)
                && nitFamiempresa != null && Objects.equals(nitFamiempresa, that.nitFamiempresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, nitFamiempresa);
    }
}
