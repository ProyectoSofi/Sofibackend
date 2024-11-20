package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(ReaEntityPK.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReaEntity {
    @Id
    private String idEvento;
    @Id
    private String nitFamiempresa;
    @Id
    private String idRecurso;
    private double afectacion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReaEntity reaEntity = (ReaEntity) o;
        return idEvento != null && Objects.equals(idEvento, reaEntity.idEvento)
                && nitFamiempresa != null && Objects.equals(nitFamiempresa, reaEntity.nitFamiempresa)
                && idRecurso != null && Objects.equals(idRecurso, reaEntity.idRecurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, nitFamiempresa, idRecurso);
    }
}
