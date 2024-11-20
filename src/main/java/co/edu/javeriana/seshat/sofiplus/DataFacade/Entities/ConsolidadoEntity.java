package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(ConsolidadoEntityPK.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsolidadoEntity {
    @Id
    private String idRecurso;
    @Id
    private String nitFamiempresa;
    private double saldo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsolidadoEntity that = (ConsolidadoEntity) o;
        return idRecurso == that.idRecurso && Double.compare(that.saldo, saldo) == 0 && Objects.equals(nitFamiempresa, that.nitFamiempresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecurso, nitFamiempresa, saldo);
    }
}
